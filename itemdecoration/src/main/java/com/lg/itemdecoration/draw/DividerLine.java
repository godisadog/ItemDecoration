package com.lg.itemdecoration.draw;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by zhenwei on 2017/8/17.
 */

public class DividerLine {
    public Drawable dividerDrawable;
    public int marginStart;
    public int marginEnd;
    public int width;

    public DividerLine(int dividerColor, int marginStart, int marginEnd, int width){
        this(new ColorDrawable(dividerColor), marginStart, marginEnd, width);
    }

    public DividerLine(Drawable dividerDrawable, int marginStart, int marginEnd, int width) {
        this.dividerDrawable = dividerDrawable;
        this.marginStart = marginStart;
        this.marginEnd = marginEnd;
        this.width = width;
    }
}
