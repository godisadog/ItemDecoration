package com.lg.itemdecoration.draw;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhenwei on 2017/8/17.
 */

public interface IDraw {
    void onDraw(Canvas c,
                RecyclerView parent,
                int orientation,
                View child,
                Rect childDecoratedBounds,
                int childSpanIndex,
                int childSpanSize,
                int spanCount,
                RecyclerView.State state);
}
