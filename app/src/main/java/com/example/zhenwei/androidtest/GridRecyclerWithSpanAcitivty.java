package com.example.zhenwei.androidtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lg.itemdecoration.CommonItemDecoration;
import com.lg.itemdecoration.offset.EqualInnerSpaOffsetStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/8/18.
 */
public class GridRecyclerWithSpanAcitivty extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 4){
                    return 3;
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.addItemDecoration(
                new CommonItemDecoration.Builder()
                        .setItemOffsetsStrategy(
                                new EqualInnerSpaOffsetStrategy
                                        .Builder()
                                        .setParentLRSpace(30)
                                        .setParentTBSpace(30)
                                        .setInnerVerticalSpace(30)
                                        .setInnerHorizontalSpace(30)
                                        .build())
                        .build());
        mRecyclerView.setLayoutManager(manager);

        setData();
    }

    private void setData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("hello! I am a item test!!!" + i);
        }

        GridWithSpanAdapter adapter = new GridWithSpanAdapter(this, list);
        mRecyclerView.setAdapter(adapter);
    }
}
