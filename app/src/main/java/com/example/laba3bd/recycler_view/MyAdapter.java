package com.example.laba3bd.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.laba3bd.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<DbItem> dataList; // список с данными из базы данных

    public MyAdapter(List<DbItem> dataList) {
        this.dataList = dataList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        TextView textViewTimeOfCreating;
        TextView textViewServiceName;
        TextView textViewDescription;
        TextView textViewPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textViewID);
            textViewTimeOfCreating = itemView.findViewById(R.id.textViewTimeOfCreating);
            textViewServiceName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.db_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DbItem dbItem = dataList.get(position);
        holder.textViewID.setText(String.valueOf(dbItem.getId()));
        holder.textViewServiceName.setText(dbItem.getServiceName());
        holder.textViewDescription.setText(dbItem.getDescription());
        holder.textViewTimeOfCreating.setText(dbItem.getRecordAddedTime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

