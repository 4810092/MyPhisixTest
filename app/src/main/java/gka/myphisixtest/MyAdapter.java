package gka.myphisixtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gka.myphisixtest.network.models.ItemModel;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {


    private List<ItemModel> list;


    public MyAdapter(List<ItemModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.setData(list.get(position));

        if (position % 2 == 0)
            holder.setLight();
        else
            holder.setDark();
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    class MyVH extends RecyclerView.ViewHolder {


        TextView tvName;
        TextView tvVolume;
        TextView tvAmount;

        MyVH(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.name);
            tvVolume = itemView.findViewById(R.id.volume);
            tvAmount = itemView.findViewById(R.id.amount);
        }

        public void setData(ItemModel itemModel) {
            tvName.setText(itemModel.getName());
            tvVolume.setText(itemModel.getVolume());
            tvAmount.setText(itemModel.getPriceAmount());

        }

        public void setLight() {
            itemView.setBackgroundResource(R.color.itemBackLight);
        }

        public void setDark() {
            itemView.setBackgroundResource(R.color.itemBackDark);

        }
    }
}
