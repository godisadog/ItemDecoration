package com.lg.itemdecoration.offset;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lg.itemdecoration.CommonItemDecoration;
import com.lg.itemdecoration.ItemDecorationUtils;

/**
 * 自定义设置上下左右的间距
 * Created by zhenwei on 2017/8/16.
 */
public class CusSpaItemOffsetStrategy implements IGetItemOffsetsStrategy {
    private Builder mBuilder;

    public CusSpaItemOffsetStrategy(Builder builder) {
        this.mBuilder = builder;
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               int orientation,
                               int position,
                               int spanIndex,
                               int spanSize,
                               int spanCount,
                               RecyclerView recyclerView,
                               RecyclerView.State state,
                               CommonItemDecoration.SPAN_POS spanPos) {
        GridLayoutManager manager = ItemDecorationUtils.getGridLayoutManager(recyclerView);

        if (orientation == RecyclerView.VERTICAL) {
            verticalItemOffsets(outRect, position, spanIndex, spanSize, spanCount, manager, state, spanPos);
        } else {
            horizontalItemOffsets(outRect, position, spanIndex, spanSize, spanCount, manager, state, spanPos);
        }
    }

    private void verticalItemOffsets(
            Rect outRect,
            int position,
            int spanIndex,
            int spanSize,
            int spanCount,
            GridLayoutManager manager,
            RecyclerView.State state,
            CommonItemDecoration.SPAN_POS spanPos) {

        int top = ItemDecorationUtils.isFirstRow(position, spanCount, manager) ? mBuilder.parentTopSpace : mBuilder.innerTopSpace;
        int bottom = ItemDecorationUtils.isLastRow(position, spanCount, state.getItemCount(), manager) ? mBuilder.parentBottomSpace : mBuilder.innerBottomSpace;
        switch (spanPos) {
            case FULL:
                outRect.set(mBuilder.parentLeftSpace, top, mBuilder.parentRightSpace, bottom);
                break;
            case LEFT:
                outRect.set(mBuilder.parentLeftSpace, top, mBuilder.innerRightSpace, bottom);
                break;
            case RIGHT:
                outRect.set(mBuilder.innerLeftSpace, top, mBuilder.parentRightSpace, bottom);
                break;
            case MIDDLE:
                outRect.set(mBuilder.innerLeftSpace, top, mBuilder.innerRightSpace, bottom);
                break;
        }
    }

    /**
     * TODO 暂时没有实现
     *
     * @param outRect
     * @param position
     * @param spanIndex
     * @param spanSize
     * @param manager
     * @param state
     * @param spanPos
     */
    private void horizontalItemOffsets(
            Rect outRect,
            int position,
            int spanIndex,
            int spanSize,
            int spanCount,
            GridLayoutManager manager,
            RecyclerView.State state,
            CommonItemDecoration.SPAN_POS spanPos) {
        int left = ItemDecorationUtils.isFirstRow(position, spanCount, manager) ? mBuilder.parentLeftSpace : mBuilder.innerLeftSpace;
        int right = ItemDecorationUtils.isLastRow(position, spanCount, state.getItemCount(), manager) ? mBuilder.parentRightSpace : mBuilder.innerRightSpace;
        switch (spanPos) {
            case FULL://上下
                outRect.set(left, mBuilder.parentTopSpace, right, mBuilder.parentBottomSpace);
                break;
            case LEFT: //上边
                outRect.set(left, mBuilder.parentTopSpace, right, mBuilder.innerBottomSpace);
                break;
            case RIGHT: //下边
                outRect.set(left, mBuilder.innerTopSpace, right, mBuilder.parentBottomSpace);
                break;
            case MIDDLE: //中间
                outRect.set(left, mBuilder.innerTopSpace, right, mBuilder.innerBottomSpace);
                break;
        }
    }

    public static class Builder {
        private int parentLeftSpace;
        private int parentTopSpace;
        private int parentRightSpace;
        private int parentBottomSpace;
        private int innerLeftSpace;
        private int innerTopSpace;
        private int innerRightSpace;
        private int innerBottomSpace;

        public Builder setParentLeftSpace(int parentLeftSpace) {
            this.parentLeftSpace = parentLeftSpace;
            return this;
        }

        public Builder setParentTopSpace(int parentTopSpace) {
            this.parentTopSpace = parentTopSpace;
            return this;
        }

        public Builder setParentRightSpace(int parentRightSpace) {
            this.parentRightSpace = parentRightSpace;
            return this;
        }

        public Builder setParentBottomSpace(int parentBottomSpace) {
            this.parentBottomSpace = parentBottomSpace;
            return this;
        }

        public Builder setInnerLeftSpace(int innerLeftSpace) {
            this.innerLeftSpace = innerLeftSpace;
            return this;
        }

        public Builder setInnerTopSpace(int innerTopSpace) {
            this.innerTopSpace = innerTopSpace;
            return this;
        }

        public Builder setInnerRightSpace(int innerRightSpace) {
            this.innerRightSpace = innerRightSpace;
            return this;
        }

        public Builder setInnerBottomSpace(int innerBottomSpace) {
            this.innerBottomSpace = innerBottomSpace;
            return this;
        }

        public CusSpaItemOffsetStrategy build(){
            return new CusSpaItemOffsetStrategy(this);
        }
    }

}
