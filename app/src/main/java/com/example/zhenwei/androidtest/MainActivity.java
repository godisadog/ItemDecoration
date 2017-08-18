package com.example.zhenwei.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转只有分割间距的LinearLayoutManager
     *
     * @param view
     */
    public void startLinearRecyclerActivity(View view) {
        startActivity(new Intent(this, LinearRecyclerActivity.class));
    }

    /**
     * 跳转有分隔线的LinearLayoutManager
     *
     * @param view
     */
    public void startLinearRecyclerWithDividerActivity(View view) {
        startActivity(new Intent(this, LinearRecyclerWithDividerLineActivity.class));
    }

    /**
     * 跳转普通的GridLayoutManager
     *
     * @param view
     */
    public void startGridRecyclerActivity(View view) {
        startActivity(new Intent(this, GridRecyclerActivity.class));
    }

    /**
     * 跳转带有跨越多列的GridLayoutManager
     *
     * @param view
     */
    public void startGridRecyclerWithSpanActivity(View view) {
        startActivity(new Intent(this, GridRecyclerWithSpanAcitivty.class));
    }
}
