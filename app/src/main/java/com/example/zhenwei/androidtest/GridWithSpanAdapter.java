package com.example.zhenwei.androidtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhenwei on 2017/8/15.
 */

public class GridWithSpanAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;
    public GridWithSpanAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new LinearItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        }else {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IHoldData) {
            ((IHoldData) holder).setData(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 4) {
            return 1;
        }else {
            return 2;
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements IHoldData<String>{
        private TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.des);
        }

        public void setData(String item){
            textView.setText(item);
        }
    }

    static class LinearItemViewHolder extends RecyclerView.ViewHolder implements IHoldData<String>{
        public LinearItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(String item) {
            ((TextView)itemView.findViewById(R.id.text)).setText(item);
        }
    }

    interface IHoldData<T>{
        void setData(T item);
    }
}
