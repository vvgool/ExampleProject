package org.project.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wiesen on 16-8-29.
 */
public class SidebarView extends View{
    private static final String TAG = "SideBar";
    public static final String[] mContent = {"A","B","C","D","E","F","G","H","I","J","K"
            ,"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};

    private int mInterVal = 5;
    private int mTextSize = 35;

    private Paint mPaint = new Paint();
    private SideBarListener mSideBarListener;
    private String mSelectString = mContent[0];
    private int mDefaultColor = Color.BLACK;
    private int mSelectColor = Color.BLUE;

    public SidebarView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float[] textSize = calculateWidthAndHeight();
        mPaint.reset();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,textSize[0],textSize[0],mPaint);
        mPaint.reset();
        mPaint.setTextSize(mTextSize);
        int startY = (int) textSize[2];
        for (String str:mContent){
            mPaint.setColor(str.equals(mSelectString)?mSelectColor:mDefaultColor);
            float v = mPaint.measureText(str);
            canvas.drawText(str,(getWidth()-v)/2, startY,mPaint);
            startY += textSize[2] + mInterVal*2;
        }
    }


    private float[] calculateWidthAndHeight(){
        float[] viewSize = new float[3];
        mPaint.setTextSize(mTextSize);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textHeight = (float) Math.ceil(fontMetrics.descent-fontMetrics.ascent);
        viewSize[0] = mPaint.measureText("M")+mInterVal*2;
        viewSize[1] = (textHeight + mInterVal*2)*mContent.length;
        viewSize[2] = textHeight;
        return viewSize;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        setSelectItem(event);
        postInvalidate();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mSideBarListener != null) {
                mSideBarListener.onItemClickListener(mSelectString);
            }
        }
        Log.i(TAG,"click:"+mSelectString);

        return true;
    }

    private void setSelectItem(MotionEvent event){
        float selectY =event.getY();
        int v = (int) (selectY / getHeight() * mContent.length);
        v = Math.abs(v);
        mSelectString = mContent[v >= mContent.length?mContent.length -1:v];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float[] size = calculateWidthAndHeight();
        setMeasuredDimension((int)size[0],(int)size[1]);
    }

    public void setOnSideBarClickListener(SideBarListener sideBarClickListener){
        mSideBarListener = sideBarClickListener;
    }

    public interface SideBarListener {
        void onItemClickListener(String str);
    }
}
