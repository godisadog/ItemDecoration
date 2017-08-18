package com.lg.itemdecoration;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zhenwei on 2017/8/16.
 */
public class ItemDecorationUtils {

    public static GridLayoutManager getGridLayoutManager(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return (GridLayoutManager) recyclerView.getLayoutManager();
        }
        return null;
    }

    public static boolean isFirstRow(int position, int spanCount, @Nullable GridLayoutManager manager) {
        if (manager != null && position < spanCount) {
            GridLayoutManager.SpanSizeLookup lookup = manager.getSpanSizeLookup();
            if (lookup.getSpanGroupIndex(position, spanCount) == 0) {
                return true;
            }
        } else if (position == 0) {
            return true;
        }
        return false;
    }

    public static boolean isLastRow(int position,
                                    int spanCount,
                                    int itemCount,
                                    @Nullable GridLayoutManager manager) {
        if (manager != null && position >= itemCount - spanCount) {
            GridLayoutManager.SpanSizeLookup lookup = manager.getSpanSizeLookup();
            int totalSpanSize = 0;
            for (int i = position; i < itemCount; i++) {
                totalSpanSize += lookup.getSpanSize(i);
            }
            if (totalSpanSize <= lookup.getSpanSize(itemCount - 1) + lookup.getSpanIndex(itemCount - 1, spanCount)) {
                return true;
            }
        } else if (position == itemCount - 1) {
            return true;
        }

        return false;
    }

    public static boolean isNextRowFullSpan(int position,
                                            int spanIndex,
                                            int spanSize,
                                            int spanCount,
                                            int itemCount,
                                            GridLayoutManager manager) {
        GridLayoutManager.SpanSizeLookup lookup = null;
        if (manager != null) {
            lookup = manager.getSpanSizeLookup();
        }
        if (lookup == null) {
            return true;
        }

        int totalSpan = spanIndex + spanSize;
        int nextRowFirstPosition = position + 1;
        for (; nextRowFirstPosition < itemCount; nextRowFirstPosition++) {
            int spanS = lookup.getSpanSize(nextRowFirstPosition);
            totalSpan += spanS;
            if (totalSpan > spanCount) {
                if (lookup.getSpanSize(nextRowFirstPosition) == spanCount) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
