package com.lg.itemdecoration.offset;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lg.itemdecoration.CommonItemDecoration;
import com.lg.itemdecoration.ItemDecorationUtils;

/**
 * 针对GridManager，有时需要调整跨越多列的之间垂直间距跟其他item之间的间距不一样
 * Created by zhenwei on 2017/8/16.
 */

public class SetFullSpanSpaceOffsets implements IGetItemOffsetsStrategy {
    private CommonItemDecoration.Builder mBuilder;
    private int fullSpanTop, fullSpanBottom;

    public SetFullSpanSpaceOffsets(int fullSpanBottom) {
        this.fullSpanBottom = fullSpanBottom;
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

       /* int top = 0;
        if (mBuilder.isShowFirstItemTopSpace() && ItemDecorationUtils.isFirstRow(position, spanCount, manager)) {
            top = fullSpanBottom;
        }

        int bottom = mBuilder.getInnerVerticalSpace();
        if (!mBuilder.isShowLastItemBottomSpace() && ItemDecorationUtils.isLastRow(position, spanCount, state.getItemCount(), manager)) {
            bottom = 0;
        } else {
            if (spanPos == CommonItemDecoration.SPAN_POS.FULL
                    || ItemDecorationUtils.isNextRowFullSpan(position, spanIndex, spanSize, spanCount, state.getItemCount(), manager)) {
                bottom = fullSpanBottom;
            }
        }*/

        /*if (orientation == RecyclerView.VERTICAL) {
            verticalItemOffsets(top, bottom, outRect, position, spanIndex, spanSize, manager, state, spanPos);
        } else {
            horizontalItemOffsets(top, bottom, outRect, position, spanIndex, spanSize, manager, state, spanPos);
        }*/
    }

    private void verticalItemOffsets(
            int top,
            int bottom,
            Rect outRect,
            int position,
            int spanIndex,
            int spanSize,
            GridLayoutManager manager,
            RecyclerView.State state,
            CommonItemDecoration.SPAN_POS spanPos) {

      /*  switch (spanPos) {
            case FULL:
                outRect.set(mBuilder.getParentLeftSpace(), top, mBuilder.getParentRightSpace(), bottom);
                break;
            case LEFT:
                outRect.set(mBuilder.getParentLeftSpace(), top, mBuilder.getInnerHorizontalSpace() / 2, bottom);
                break;
            case RIGHT:
                outRect.set(mBuilder.getInnerHorizontalSpace() / 2, top, mBuilder.getParentRightSpace(), bottom);
                break;
            case MIDDLE:
                outRect.set(mBuilder.getInnerHorizontalSpace() / 2, top, mBuilder.getInnerHorizontalSpace() / 2, bottom);
                break;
        }*/
    }

    /**
     * TODO 暂时没有实现
     *
     * @param top
     * @param bottom
     * @param outRect
     * @param position
     * @param spanIndex
     * @param spanSize
     * @param manager
     * @param state
     * @param spanPos
     */
    private void horizontalItemOffsets(
            int top,
            int bottom,
            Rect outRect,
            int position,
            int spanIndex,
            int spanSize,
            GridLayoutManager manager,
            RecyclerView.State state,
            CommonItemDecoration.SPAN_POS spanPos) {

    }
}
