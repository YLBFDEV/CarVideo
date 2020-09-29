package com.ylbf.appbase.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ylbf.appbase.R;


/**
 * Created by ylbf_ on 2016/4/18.
 */
public class BaseSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable mDrawable;
    private int mSpace;

    public BaseSpacesItemDecoration(Context context, int space) {
        this(context, space, R.drawable.shape_divd);
    }

    public BaseSpacesItemDecoration(Context context, int space, int resId) {
        this.mSpace = space;
        //在这里我们传入作为Divider的Drawable对象
        mDrawable = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.top = 0;
        outRect.bottom = mSpace;

        if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
            if (position < ((StaggeredGridLayoutManager) parent.getLayoutManager())
                    .getSpanCount()) {
                // 利用item的margin配合RecyclerView的margin值使得间隔相等，这里只需设第一行item的相对顶部的高度
                outRect.top = mSpace;
            }
        } else if (parent.getLayoutManager() instanceof GridLayoutManager) {
            if (position < ((GridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
                // 保证第一行有相对顶部有高度
                outRect.top = mSpace;
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            if (position == 0) {
                // 保证第一行有相对顶部有高度
                outRect.top = mSpace;
            }
        }
    }
}
