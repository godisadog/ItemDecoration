package com.lg.itemdecoration.offset;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lg.itemdecoration.CommonItemDecoration;
import com.lg.itemdecoration.ItemDecorationUtils;

/**
 * 设定相同间距的offset
 * Created by zhenwei on 2017/8/16.
 */
public class EqualInnerSpaOffsetStrategy implements IGetItemOffsetsStrategy {
    private Builder mBuilder;

    public EqualInnerSpaOffsetStrategy(Builder builder) {
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
        int top = ItemDecorationUtils.isFirstRow(position, spanCount, manager) ? mBuilder.parentTBSpace : mBuilder.innerVerticalSpace / 2;
        int bottom = ItemDecorationUtils.isLastRow(position, spanCount, state.getItemCount(), manager) ? mBuilder.parentTBSpace : mBuilder.innerVerticalSpace / 2;

        switch (spanPos) {
            case FULL:
                outRect.set(mBuilder.parentLRSpace, top, mBuilder.parentLRSpace, bottom);
                break;
            case LEFT:
                outRect.set(mBuilder.parentLRSpace, top, mBuilder.innerHorizontalSpace / 2, bottom);
                break;
            case RIGHT:
                outRect.set(mBuilder.innerHorizontalSpace / 2, top, mBuilder.parentLRSpace, bottom);
                break;
            case MIDDLE:
                outRect.set(mBuilder.innerHorizontalSpace / 2, top, mBuilder.innerHorizontalSpace / 2, bottom);
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
        int left = ItemDecorationUtils.isFirstRow(position, spanCount, manager) ? mBuilder.parentLRSpace : mBuilder.innerHorizontalSpace / 2;
        int right = ItemDecorationUtils.isLastRow(position, spanCount, state.getItemCount(), manager) ? mBuilder.parentLRSpace : mBuilder.innerHorizontalSpace / 2;
        switch (spanPos) {
            case FULL:
                outRect.set(left, mBuilder.parentTBSpace, right, mBuilder.parentTBSpace);
                break;
            case LEFT:
                outRect.set(left, mBuilder.parentTBSpace, right, mBuilder.innerVerticalSpace / 2);
                break;
            case RIGHT:
                outRect.set(left, mBuilder.innerVerticalSpace / 2, right, mBuilder.parentTBSpace);
                break;
            case MIDDLE:
                outRect.set(left, mBuilder.innerVerticalSpace / 2, right, mBuilder.innerVerticalSpace / 2);
                break;
        }
    }

    public static class Builder {
        private int parentLRSpace;
        private int parentTBSpace;
        private int innerVerticalSpace;
        private int innerHorizontalSpace;

        public Builder setParentLRSpace(int parentLRSpace) {
            this.parentLRSpace = parentLRSpace;
            return this;
        }

        public Builder setParentTBSpace(int parentTBSpace) {
            this.parentTBSpace = parentTBSpace;
            return this;
        }

        public Builder setInnerVerticalSpace(int innerVerticalSpace) {
            this.innerVerticalSpace = innerVerticalSpace;
            return this;
        }

        public Builder setInnerHorizontalSpace(int innerHorizontalSpace) {
            this.innerHorizontalSpace = innerHorizontalSpace;
            return this;
        }

        public EqualInnerSpaOffsetStrategy build() {
            return new EqualInnerSpaOffsetStrategy(this);
        }
    }
}
