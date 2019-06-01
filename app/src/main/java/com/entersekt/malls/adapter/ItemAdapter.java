package com.entersekt.malls.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.entersekt.malls.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<String> items;

    private OnItemSelectListener listener;

    public interface OnItemSelectListener {
        void onItemSelect(int itemId, String itemName);
    }

    public ItemAdapter(List<String> items) {
        this.items = items;
    }

    public ItemAdapter(List<String> items, OnItemSelectListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        holder.nameView.setOnClickListener(v -> {
            if (listener != null) {
                int adapterPosition = holder.getAdapterPosition();
                listener.onItemSelect(adapterPosition, items.get(adapterPosition));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bind(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTv);
        }

        public void bind(String itemName) {
            nameView.setText(itemName);
        }
    }
}
