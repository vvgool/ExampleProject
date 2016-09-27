package org.project.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
        Bitmap btDrawable = ((BitmapDrawable)drawable).getBitmap();
        int width = btDrawable.getWidth() > btDrawable.getHeight() ?
                btDrawable.getHeight():btDrawable.getWidth();
        btDrawable = Bitmap.createBitmap(btDrawable,(btDrawable.getWidth() - width)/2
                ,(btDrawable.getHeight() -width)/2,width,width);
        setImageBitmap(btDrawable);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = widthMeasureSpec > heightMeasureSpec ? heightMeasureSpec:widthMeasureSpec;
        setMeasuredDimension(width,width);
    }
}
