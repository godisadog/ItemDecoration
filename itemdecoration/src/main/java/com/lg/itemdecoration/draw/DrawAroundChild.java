package com.lg.itemdecoration.draw;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhenwei on 2017/8/17.
 */

public class DrawAroundChild implements IDraw {
    private Builder mBuilder;
    public DrawAroundChild(Builder builder){
        this.mBuilder = builder;
    }

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

        int left = childDecoratedBounds.left;
        int top = childDecoratedBounds.top;
        int right = childDecoratedBounds.right;
        int bottom = childDecoratedBounds.bottom;

        //drawLeft
        if (mBuilder.leftDividerLine != null) {
            DividerLine leftDividerLine = mBuilder.leftDividerLine;
            leftDividerLine
                    .dividerDrawable
                    .setBounds(left - leftDividerLine.width / 2,
                            top + leftDividerLine.marginStart,
                            left + leftDividerLine.width / 2,
                            bottom - leftDividerLine.marginEnd);
            leftDividerLine
                    .dividerDrawable.draw(c);
        }

        //drawTop
        if (mBuilder.topDividerLine != null) {
            DividerLine topDividerLine = mBuilder.topDividerLine;
            topDividerLine
                    .dividerDrawable
                    .setBounds(left + topDividerLine.marginStart,
                            top - topDividerLine.width / 2,
                            right - topDividerLine.marginEnd,
                            top + topDividerLine.width / 2);
            topDividerLine
                    .dividerDrawable.draw(c);
        }

        //drawright
        if (mBuilder.rightDividerLine != null) {
            DividerLine rightDividerLine = mBuilder.rightDividerLine;
            rightDividerLine
                    .dividerDrawable
                    .setBounds(right - rightDividerLine.width / 2,
                            top + rightDividerLine.marginStart,
                            right + rightDividerLine.width / 2,
                            bottom - rightDividerLine.marginEnd);
            rightDividerLine
                    .dividerDrawable.draw(c);
        }

        //drawBottom
        if (mBuilder.bottomDivierLine != null) {
            DividerLine bottomDivierLine = mBuilder.bottomDivierLine;
            bottomDivierLine
                    .dividerDrawable
                    .setBounds(left + bottomDivierLine.marginStart,
                            bottom - bottomDivierLine.width / 2,
                            right - bottomDivierLine.marginEnd,
                            bottom + bottomDivierLine.width / 2);
            bottomDivierLine
                    .dividerDrawable.draw(c);
        }
    }

    /**
     * DrawAroundChild的生成器
     */
    public static class Builder {
        private DividerLine leftDividerLine,
                topDividerLine,
                rightDividerLine,
                bottomDivierLine;
        public Builder setLeftDividerLine(DividerLine leftDividerLine){
            this.leftDividerLine = leftDividerLine;
            return this;
        }

        public Builder setRightDividerLine(DividerLine rightDividerLine){
            this.rightDividerLine = rightDividerLine;
            return this;
        }

        public Builder setTopDividerLine(DividerLine topDividerLine) {
            this.topDividerLine = topDividerLine;
            return this;
        }

        public Builder setBottomDividerLine(DividerLine bottomDividerLine) {
            this.bottomDivierLine = bottomDividerLine;
            return this;
        }

        public DrawAroundChild build(){
            return new DrawAroundChild(this);
        }
    }
}
