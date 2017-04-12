package example.toong.firebaserecyclerviewwithloadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    final List<Question> questionList = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;
    private int currentPage = 0;
    private static final int TOTAL_ITEM_EACH_LOAD = 10;
    private ProgressBar mProgressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAdapter = new RecyclerViewAdapter(questionList);
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        });
        loadData();
    }

    private void initTestData() {
        for (int i = 0; i < 100; i++) {
            Question question2 = new Question();
            question2.setNumber(i);
            question2.setContent("Question " + i);
            question2.setMark((int) (new Random().nextInt(10)));
            mDatabase.child("questions").push().setValue(question2);
        }
    }

    private void loadData() {
        mDatabase.child("questions")
                .limitToFirst(TOTAL_ITEM_EACH_LOAD)
                .startAt(currentPage*TOTAL_ITEM_EACH_LOAD)
                .orderByChild("number")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChildren()){
                            Toast.makeText(MainActivity.this, "No more questions", Toast.LENGTH_SHORT).show();
                            currentPage--;
                        }
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Question question = data.getValue(Question.class);
                            questionList.add(question);
                            mAdapter.notifyDataSetChanged();
                        }
                        mProgressBar.setVisibility(RecyclerView.GONE);
                    }


                    @Override public void onCancelled(DatabaseError databaseError) {
                        mProgressBar.setVisibility(RecyclerView.GONE);
                    }
                });
    }

    private void loadMoreData(){
        currentPage++;
        loadData();
        mProgressBar.setVisibility(RecyclerView.VISIBLE);
    }
}
