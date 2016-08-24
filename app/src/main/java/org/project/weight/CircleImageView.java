package org.project.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import org.project.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by wiesen on 2016/8/17.
 */
public class CircleImageView extends ImageView {
    private int mCircleBorderSize;
    private int mCircleBorderColor;
    private int mDefaultBorderColor = Color.parseColor("#000000");
    private int mRadius=0;



    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mCircleBorderSize = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_view_border_size,0);
        mCircleBorderColor = typedArray.getColor(R.styleable.CircleImageView_view_border_color,mDefaultBorderColor);
        typedArray.recycle();
    }



    public void setBorderSize(int borderSize){
        mCircleBorderSize = borderSize;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void setBorderColor(int borderColor){
        mCircleBorderColor = getContext().getColor(borderColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRadius = getWidth()/2-mCircleBorderSize/2-1;
        Bitmap srcBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        if (srcBitmap == null) return;
        drawCircleViewBorder(canvas);
//        drawCircleViewFunctionOne(canvas,srcBitmap);
        drawCircleViewFunctionTwo(canvas,srcBitmap);
//        drawCircleViewFunctionThree(canvas,srcBitmap);
    }


    private void drawCircleViewFunctionOne(Canvas canvas, Bitmap bitmap){
        Bitmap dstBitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        Canvas dstCanvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        paint.setAntiAlias(true);
        dstCanvas.drawCircle(getWidth()/2,getHeight()/2,mRadius - mCircleBorderSize/2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        dstCanvas.drawBitmap(bitmap,(getWidth()-bitmapWidth)/2,(getHeight()-bitmapHeight)/2,paint);
        canvas.drawBitmap(dstBitmap,0,0,null);
    }

    private void drawCircleViewFunctionTwo(Canvas canvas,Bitmap bitmap){
        int width = bitmap.getWidth() > bitmap.getHeight()? bitmap.getHeight():bitmap.getWidth();
        Bitmap dstBitmap = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);
        Canvas dstCanvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        paint.setAntiAlias(true);
        dstCanvas.drawCircle(width/2,width/2,width/2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        dstCanvas.drawBitmap(bitmap,(width-bitmapWidth)/2,(width-bitmapHeight)/2,paint);
        Matrix matrix = new Matrix();
        int wantWidth = mRadius *2 -mCircleBorderSize;
        matrix.setScale(wantWidth/width,wantWidth/width);
        Bitmap finalBitmap = Bitmap.createScaledBitmap(dstBitmap,wantWidth,wantWidth,false);
        canvas.drawBitmap(finalBitmap,mCircleBorderSize+1,mCircleBorderSize+1,null);
    }

    private void drawCircleViewFunctionThree(Canvas canvas,Bitmap bitmap){
        canvas.save();
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.addCircle(getWidth()/2,getHeight()/2,mRadius-mCircleBorderSize/2, Path.Direction.CW);
        canvas.clipPath(path);
        int wantWidth = mRadius *2 -mCircleBorderSize;
        Bitmap finalBitmap = Bitmap.createScaledBitmap(bitmap,wantWidth,wantWidth,false);
        canvas.drawBitmap(finalBitmap,mCircleBorderSize+1,mCircleBorderSize+1,paint);
        canvas.restore();
    }

    private Bitmap disposeBitmap(Bitmap bitmap,int width){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.size(),options);
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        int ratio = 1;
        if (srcWidth >width && srcHeight >width){
            ratio = srcWidth > srcHeight ? srcHeight/width : srcWidth/width;
        }
        options.inSampleSize = ratio;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.size(),options);
    }

    /**
     * draw border
     * @param canvas
     */
    private void drawCircleViewBorder(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mCircleBorderSize);
        paint.setColor(mCircleBorderColor);
        paint.setDither(true);
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2,getHeight()/2,mRadius,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(width,width);
    }
}
