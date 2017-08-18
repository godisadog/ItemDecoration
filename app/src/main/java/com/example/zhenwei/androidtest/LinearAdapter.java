package com.example.zhenwei.androidtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhenwei on 2017/8/18.
 */

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearItemViewHolder> {
    private Context context;
    private List<String> list;

    public LinearAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public LinearItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));

    }

    @Override
    public void onBindViewHolder(LinearItemViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class LinearItemViewHolder extends RecyclerView.ViewHolder {
        public LinearItemViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(String item) {
            ((TextView)itemView.findViewById(R.id.text)).setText(item);
        }
    }
}
