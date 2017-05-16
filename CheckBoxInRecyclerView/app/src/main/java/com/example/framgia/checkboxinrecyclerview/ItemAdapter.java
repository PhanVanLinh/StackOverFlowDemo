package com.example.framgia.checkboxinrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
 
    private List<Item> itemList;
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckBox mCheckBox;
 
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
 
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Item item = itemList.get(position);
        holder.title.setText(item.getTitle());

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.get(position).setChecked(!item.isChecked());
            }
        });

        holder.mCheckBox.setChecked(item.isChecked());
    }
 
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}