package example.toong.firebaserecycleradaptermutipleviewtype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

class PersonAdapter extends FirebaseRecyclerAdapter<Person, RecyclerView.ViewHolder> {

    PersonAdapter(Class<Person> modelClass, DatabaseReference ref) {
        super(modelClass, 0, RecyclerView.ViewHolder.class, ref);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Person.PersonType.TYPE_1:
                View userType1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_person_type_1, parent, false);
                return new PersonType1ViewHolder(userType1);
            case Person.PersonType.TYPE_2:
                View userType2 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_person_type_2, parent, false);
                return new PersonType2ViewHolder(userType2);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Person model,
            int position) {
        if (viewHolder instanceof PersonType1ViewHolder) {
            ((PersonType1ViewHolder) viewHolder).name.setText("Full name: " + model.getName());
            return;
        }

        if (viewHolder instanceof PersonType2ViewHolder) {
            ((PersonType2ViewHolder) viewHolder).name.setText("Full name: " + model.getName());
            ((PersonType2ViewHolder) viewHolder).tvAge.setText("Age: " + model.getAge());
        }
    }

    private class PersonType1ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        PersonType1ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    private class PersonType2ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView tvAge;

        PersonType2ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }
}