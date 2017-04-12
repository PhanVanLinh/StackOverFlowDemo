package example.toong.firebaseupdatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Masjed masjed;
    private boolean isFirstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masjed = new Masjed("userId", "", "name", "address", "phone", true, 10);
                mDatabase.child("masjed")
                        .push()
                        .setValue(masjed, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError,
                                    DatabaseReference databaseReference) {
                                masjed.setId(databaseReference.getKey());

                                mDatabase.child("masjed")
                                        .child(databaseReference.getKey())
                                        .child("id")
                                        .setValue(databaseReference.getKey());
                            }
                        });
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (masjed == null) {
                    Toast.makeText(MainActivity.this, "Need add before update", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                masjed.setName("new name");
                masjed.setAddress("new address");
                mDatabase.child("masjed").child(masjed.getId()).setValue(masjed);
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (masjed == null) {
                    Toast.makeText(MainActivity.this, "Need add before delete", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                mDatabase.child("masjed").child(masjed.getId()).removeValue();
                masjed = null;
            }
        });

        /**
         * For print test
         */
        mDatabase.child("masjed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (isFirstLaunch) {
                    deleteAllWhenFirstLaunch(dataSnapshot);
                }
                isFirstLaunch = false;
                List<Masjed> masjedList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Masjed masjed = postSnapshot.getValue(Masjed.class);
                    masjedList.add(masjed);
                }
                ((TextView) findViewById(R.id.content)).setText(Masjed.listToString(masjedList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * This is just a demo app and I don't want to have more data, so I will delete all data
     * when first launch app
     */
    private void deleteAllWhenFirstLaunch(DataSnapshot dataSnapshot) {
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            mDatabase.child("masjed").child(postSnapshot.getKey()).removeValue();
        }
    }
}
