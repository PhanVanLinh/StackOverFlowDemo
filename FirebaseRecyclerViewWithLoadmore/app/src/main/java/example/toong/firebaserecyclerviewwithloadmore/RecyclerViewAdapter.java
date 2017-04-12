package example.toong.firebaserecyclerviewwithloadmore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by phanvanlinh on 12/04/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Question> mQuestionList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, mark;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.content);
            mark = (TextView) view.findViewById(R.id.mark);
        }
    }

    RecyclerViewAdapter(List<Question> mQuestionList) {
        this.mQuestionList = mQuestionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Question question = mQuestionList.get(position);
        holder.title.setText(question.getContent());
        holder.mark.setText("" + question.getMark());
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }
}