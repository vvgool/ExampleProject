package org.project.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wiesen on 16-8-24.
 */
public class GridImageView extends ImageView {
    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        Rect bounds = drawable.getBounds();
        int btWidth = bounds.width() > bounds.height() ?
                bounds.height():bounds.width();
        int subWidth = (bounds.width() - btWidth)/2;
        int subHeight = (bounds.height() - btWidth) /2;
        drawable.setBounds(bounds.left + subWidth ,bounds.top + subHeight
                ,bounds.right - subWidth,bounds.bottom - subHeight);

        setImageDrawable(drawable);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = widthMeasureSpec > heightMeasureSpec ? heightMeasureSpec:widthMeasureSpec;
        setMeasuredDimension(width,width);
    }
}
