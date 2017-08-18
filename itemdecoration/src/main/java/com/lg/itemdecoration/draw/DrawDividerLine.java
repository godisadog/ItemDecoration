package com.lg.itemdecoration.draw;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lg.itemdecoration.draw.IDraw;

/**
 * Created by zhenwei on 2017/8/17.
 */

public class DrawDividerLine implements IDraw {
    private Drawable divider;
    private int marginLeft;
    private int marginRight;
    private int dividerWidth;

    public DrawDividerLine(Drawable divider) {
        this(divider, 0, 0, Math.max(1, divider.getIntrinsicHeight()));
    }

    public DrawDividerLine(int dividerColor){
        this(dividerColor, 0, 0, 1);
    }

    public DrawDividerLine(int dividerColor, int dividerWidth) {
        this(dividerColor, 0, 0, dividerWidth);
    }

    public DrawDividerLine(Drawable divider, int dividerWidth) {
        this(divider, 0, 0, dividerWidth);
    }

    public DrawDividerLine(int dividerColor, int marginLeft, int marginRight, int dividerWidth) {
        this(new ColorDrawable(dividerColor), marginLeft, marginRight, dividerWidth);
    }

    public DrawDividerLine(Drawable divider, int marginLeft, int marginRight, int dividerWidth) {
        this.divider = divider;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.dividerWidth = dividerWidth;
    }

    /**
     * 只画水平或竖向的线
     * @param c
     * @param parent
     * @param child
     * @param childSpanIndex
     * @param childSpanSize
     * @param spanCount
     * @param state
     */
    @Override
    public void onDraw(Canvas c,
                       RecyclerView parent,
                       int orientation,
                       View child,
                       Rect childDecoratedBounds,
                       int childSpanIndex,
                       int childSpanSize,
                       int spanCount,
                       RecyclerView.State state) {
        if (childSpanIndex == 0) {
            int left = parent.getPaddingLeft();
            int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            int bottom = childDecoratedBounds.bottom + dividerWidth / 2;
            int top = bottom - dividerWidth;
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

}
