package com.lg.itemdecoration.offset;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.lg.itemdecoration.CommonItemDecoration;

/**
 * Created by zhenwei on 2017/8/16.
 */

public interface IGetItemOffsetsStrategy {
    void getItemOffsets(Rect outRect,
                        int orientation,
                        int position,
                        int spanIndex,
                        int spanSize,
                        int spanCount,
                        RecyclerView recyclerView,
                        RecyclerView.State state,
                        CommonItemDecoration.SPAN_POS spanPos);
}
