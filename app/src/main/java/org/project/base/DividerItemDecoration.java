package org.project.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ljdy on 2016/8/18.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDividerDrawable;

    public DividerItemDecoration(Context context,int linearLayoutOrientation) {
        mOrientation = linearLayoutOrientation;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDividerDrawable = typedArray.getDrawable(0);

        typedArray.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mOrientation == LinearLayoutManager.VERTICAL){
            drawVertivalDivider(c,parent);
        }else if(mOrientation == LinearLayoutManager.HORIZONTAL){
            drawHorizontalDivider(c,parent);
        }
    }

    private void drawVertivalDivider(Canvas canvas,RecyclerView parent){
        int left = parent.getLeft();
        int right = parent.getRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++){
            View childView = parent.getChildAt(i);
            int top = childView.getBottom();
            int bottom = top + mDividerDrawable.getIntrinsicHeight();
            mDividerDrawable.setBounds(left,top,right,bottom);
            mDividerDrawable.draw(canvas);
        }
    }

    private void drawHorizontalDivider(Canvas canvas,RecyclerView parent){
        int top = parent.getTop();
        int bottom = parent.getBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++){
            View childView = parent.getChildAt(i);
            int left = childView.getRight();
            int right = left + mDividerDrawable.getIntrinsicWidth();
            mDividerDrawable.setBounds(left,top,right,bottom);
            mDividerDrawable.draw(canvas);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0,mDividerDrawable.getIntrinsicHeight());
        }else if (mOrientation == LinearLayoutManager.HORIZONTAL){
            outRect.set(0,0,mDividerDrawable.getIntrinsicWidth(),0);
        }
    }
}
