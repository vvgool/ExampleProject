package org.project.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import org.project.R;

/**
 * Created by wiesen on 16-8-22.
 */
public class PictureImageView extends ImageView{
    private static final int MAX_SIZE = 30;
    private int mDefaultBorderColor = Color.parseColor("#ffafff");
    private int mBorderColor ;
    private int mBorderSize;
    public PictureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PictureImageView);
        mBorderColor = typedArray.getColor(R.styleable.PictureImageView_view_border_color,mDefaultBorderColor);
        mBorderSize  = typedArray.getDimensionPixelSize(R.styleable.PictureImageView_view_border_size,MAX_SIZE);
        typedArray.recycle();
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        Bitmap btDrawable = ((BitmapDrawable)drawable).getBitmap();
        btDrawable = ThumbnailUtils.extractThumbnail(btDrawable,getWidth(),getHeight());

        Bitmap dst = drawRoundRect();
        Matrix matrix = new Matrix();
        matrix.postTranslate((getWidth()-dst.getWidth())/2,(getHeight()-dst.getHeight())/2);
        matrix.postRotate(5,dst.getWidth()/2,dst.getHeight()/2);
        canvas.drawBitmap(dst,matrix,null);
        canvas.drawBitmap(dst,(getWidth()-dst.getWidth())/2,(getHeight()-dst.getHeight())/2,null);
        Bitmap src = Bitmap.createScaledBitmap(btDrawable,dst.getWidth() -mBorderSize*2,dst.getHeight() - mBorderSize*2,false);
        canvas.drawBitmap(src,(getWidth() - src.getWidth())/2,(getHeight() - src.getHeight())/2,null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Bitmap drawRoundRect(){
        Paint paint = new Paint();
        paint.setColor(mBorderColor);
        paint.setAntiAlias(true);
        paint.setDither(true);
        int width = getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width - MAX_SIZE*2,width - MAX_SIZE*2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRoundRect(0,0,bitmap.getWidth(),bitmap.getHeight(),5,5,paint);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = widthMeasureSpec > heightMeasureSpec? heightMeasureSpec:widthMeasureSpec;
        setMeasuredDimension(width,width);
    }
}
