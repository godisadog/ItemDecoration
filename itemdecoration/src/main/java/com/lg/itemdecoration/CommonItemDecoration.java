package com.lg.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.lg.itemdecoration.draw.IDraw;
import com.lg.itemdecoration.offset.IGetItemOffsetsStrategy;

/**
 * 现在主要实现了item之间的空隙
 * draw的部分还没有写，有时间在写吧！！！
 * Created by zhenwei on 2017/8/15.
 */
public class CommonItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpanCount;

    private Rect mDecoratedBounds;
    private IGetItemOffsetsStrategy getItemOffsetsStrategy;
    private IDraw mIDraw;

    private Paint paint;
    public CommonItemDecoration(Builder builder) {
        this.getItemOffsetsStrategy = builder.itemOffsetsStrategy;
        mIDraw = builder.draw;

        mDecoratedBounds = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    int cc = 0;
    int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLUE};

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        GridLayoutManager manager = ItemDecorationUtils.getGridLayoutManager(parent);
        int orientation = getOrientation(parent);
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(view, mDecoratedBounds);

            int spanIndex = 0;
            int spanSize = 1; //兼容LinearLayout
            if (manager != null) {
                GridLayoutManager.SpanSizeLookup lookup = manager.getSpanSizeLookup();
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                Log.d("zhenwei", "position=" + childAdapterPosition + "  bounds=" + mDecoratedBounds);
                spanIndex = lookup.getSpanIndex(childAdapterPosition, mSpanCount);
                spanSize = lookup.getSpanSize(childAdapterPosition);
                paint.setColor(colors[cc++ % colors.length]);
//                c.drawRect(mDecoratedBounds, paint);
            }
            if (mIDraw != null) {
                mIDraw.onDraw(c, parent, orientation, view, mDecoratedBounds, spanIndex, spanSize, mSpanCount, state);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        initRecyclerView(parent);
        int position = parent.getChildAdapterPosition(view);

        GridLayoutManager manager = ItemDecorationUtils.getGridLayoutManager(parent);
        int spanIndex = 0;
        int spanSize = 1;//默认值是1，兼容linearLayoutManager
        if (manager != null) {
            GridLayoutManager.SpanSizeLookup lookup = ((GridLayoutManager) manager).getSpanSizeLookup();
            spanSize = lookup.getSpanSize(position);
            spanIndex = lookup.getSpanIndex(position, mSpanCount);
        }

        int orientation = getOrientation(parent);
        SPAN_POS spanPos = getSpanPosOfAdapterPos(spanIndex, spanSize, mSpanCount);

        if (this.getItemOffsetsStrategy != null) {
            this.getItemOffsetsStrategy.getItemOffsets(outRect, orientation, position, spanIndex, spanSize, mSpanCount, parent, state, spanPos);
        }
    }

    private int getOrientation(RecyclerView recyclerView) {
        int orientation;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        // default LayoutManager hasn't getOrientation() method
        if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        } else {
            orientation = RecyclerView.VERTICAL;
        }
        return orientation;
    }

    private SPAN_POS getSpanPosOfAdapterPos(int spanIndex, int spanSize, int spanCount) {
        if (spanIndex == 0 && spanSize == spanCount) {
            return SPAN_POS.FULL;
        } else if (spanIndex == 0) {
            return SPAN_POS.LEFT;
        } else if (spanIndex == spanCount - 1
                || spanIndex + spanSize == spanCount) {
            return SPAN_POS.RIGHT;
        } else {
            return SPAN_POS.MIDDLE;
        }
    }

    private void initRecyclerView(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        mSpanCount = 1;
        if (layoutManager instanceof GridLayoutManager) {
            mSpanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            GridLayoutManager.SpanSizeLookup lookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            if (!lookup.isSpanIndexCacheEnabled()) {
                lookup.setSpanIndexCacheEnabled(true);
            }
        }
    }

    public enum SPAN_POS {
        FULL,
        LEFT,
        MIDDLE,
        RIGHT
    }

    public static class Builder {
        private IGetItemOffsetsStrategy itemOffsetsStrategy;
        private IDraw draw;

        public Builder setItemOffsetsStrategy(IGetItemOffsetsStrategy itemOffsetsStrategy) {
            this.itemOffsetsStrategy = itemOffsetsStrategy;
            return this;
        }

        public Builder setIDraw(IDraw draw) {
            this.draw = draw;
            return this;
        }

        public CommonItemDecoration build() {
            return new CommonItemDecoration(this);
        }
    }

    /*public static class Builder {
        public RecyclerView recyclerView;

        private int parentLeftSpace;
        private int parentRightSpace;
        private int innerVerticalSpace;
        private int innerHorizontalSpace;
        private boolean showLastItemBottomSpace;
        private boolean showFirstItemTopSpace;
        private IGetItemOffsetsStrategy itemOffsetsStrategy;
        private IDraw iDraw;

        public Builder(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public Builder setParentLeftSpace(int parentLeftSpace) {
            this.parentLeftSpace = parentLeftSpace;
            return this;
        }

        public Builder setParentRightSpace(int parentRightSpace) {
            this.parentRightSpace = parentRightSpace;
            return this;
        }

        public Builder setInnerVerticalSpace(int innerVerticaleSpace) {
            this.innerVerticalSpace = innerVerticaleSpace;
            return this;
        }

        public Builder setInnerHorizontalSpace(int innerHorizontalSpace) {
            this.innerHorizontalSpace = innerHorizontalSpace;
            return this;
        }

        public Builder showLastItemBottomSpace(boolean showLastItemBottomSpace) {
            this.showLastItemBottomSpace = showLastItemBottomSpace;
            return this;
        }

        public Builder showFirstItemTopSpace(boolean showFirstItemTopSpace) {
            this.showFirstItemTopSpace = showFirstItemTopSpace;
            return this;
        }

        public Builder setItemOffsetsStrategy(IGetItemOffsetsStrategy itemOffsetsStrategy) {
            this.itemOffsetsStrategy = itemOffsetsStrategy;
            return this;
        }

        public Builder setIDraw(IDraw draw){
            this.iDraw = draw;
            return this;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }

        public int getParentLeftSpace() {
            return parentLeftSpace;
        }

        public int getParentRightSpace() {
            return parentRightSpace;
        }

        public int getInnerVerticalSpace() {
            return innerVerticalSpace;
        }

        public int getInnerHorizontalSpace() {
            return innerHorizontalSpace;
        }

        public boolean isShowLastItemBottomSpace() {
            return showLastItemBottomSpace;
        }

        public boolean isShowFirstItemTopSpace() {
            return showFirstItemTopSpace;
        }

        public IGetItemOffsetsStrategy getItemOffsetsStrategy() {
            return itemOffsetsStrategy;
        }

        public CommonItemDecoration build() {
            return new CommonItemDecoration(this);
        }
    }*/
}
