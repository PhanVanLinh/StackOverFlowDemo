package example.toong.recyclerviewmutilpleitemtypesupportshowhideitemtype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM_ONE = 1;
    private static final int TYPE_ITEM_TWO = 1;
    private boolean isShowItemTwo;
    String[] data;

    public HeaderAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_ONE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_1, parent, false);
            return new VHItemOne(view);
        } else if (viewType == TYPE_ITEM_TWO) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_2, parent, false);
            return new VHItemOne(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
            return new VHHeader(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHHeader) {

        }else if (holder instanceof VHItemOne) {

        } else if (holder instanceof VHItemTwo) {
            ((VHItemTwo) holder).mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isShowItemTwo = !isShowItemTwo;
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        if(isShowItemTwo && position %2 == 0){
            return TYPE_ITEM_TWO;
        }
        return TYPE_ITEM_ONE;
    }

    // condition for header
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    // getItem -1 because we have add 1 header
    private String getItem(int position) {
        return data[position - 1];
    }

    class VHItemOne extends RecyclerView.ViewHolder {

        public VHItemOne(View itemView) {
            super(itemView);
        }
    }

    class VHItemTwo extends RecyclerView.ViewHolder {
        Button mButton;

        public VHItemTwo(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.button);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        public VHHeader(View itemView) {
            super(itemView);
        }
    }
}