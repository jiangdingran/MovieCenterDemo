package com.jx.test.sift;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 武晓瑞 on 2017/12/5.
 */

public class ScrollViewWithListView  extends RecyclerView {
    public ScrollViewWithListView(android.content.Context context,android.util.AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * Integer.MAX_VALUE >> 2,如果不设置，系统默认设置是显示两条
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
