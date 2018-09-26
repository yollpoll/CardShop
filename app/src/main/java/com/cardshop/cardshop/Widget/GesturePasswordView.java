package com.cardshop.cardshop.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ScreenUtils;

/**
 * Created by 鹏祺 on 2017/2/9.
 */

public class GesturePasswordView extends View {
    public static final int STATE_NORMAL = 0;//没有手指触碰
    public static final int STATE_DOWN = 1;//手指触碰
    public static final int STATE_UP = 2;//手指抬起、选中
    private static final int PAINT_WIDTH = 2;//画笔粗细，dp单位

    private OnStateChangeListener onStateChangeListener;

    private Context mContext;

    public GesturePasswordView(Context context) {
        super(context);
        init(context);
    }

    public GesturePasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GesturePasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Paint mPaint;
    private int color;
    private int state = STATE_NORMAL;
    private int radius = 0;

    private void init(Context context) {
        mPaint = new Paint();
        mContext = context;
    }

    //设置选中颜色
    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public float getCenterX() {
        return (getLeft() + getRight()) / 2;
    }

    public float getCenterY() {
        return (getTop() + getBottom()) / 2;
    }
//    public boolean isCross(double x1,double y1,double x2,double y2){
//        int x=(getLeft()+getRight())/2;
//        int y=(getTop()+getBottom())/2;
//        double length1=Math.sqrt(Math.pow((x-x1),2)+Math.pow((y-y1),2));
//        double length2=Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y2),2));
//        Log.i("spq",length1+"   "+length2+"  "+radius);
//        if(length1-radius>0||length2-radius>0){
////            //端点不在圆内
////            double k=(x1-x2)/(y1-y2);
////            double b=y1-k*x1;
////            //直线到点的距离
////            double length=Math.abs((-k)*x+y-b)/Math.sqrt(Math.pow(k,2)+1);
////            if(length<=radius){
////                //在圆内
////                return true;
////            }else {
////                return false;
////            }
//            return false;
//        }else {
//            //两个端点在圆内
//            return true;
//        }
//    }

    //设置选中状态
    public void setState(int state) {
        this.state = state;
        if (null != onStateChangeListener) {
            onStateChangeListener.onChange(state, this);
        }
        invalidate();
    }

    public int getState() {
        return state;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state) {
            case STATE_NORMAL:
                //一般状态
                mPaint.setColor(mContext.getResources().getColor(R.color.gesture_gray));
                mPaint.setStrokeWidth(ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setAntiAlias(true);
                radius = (getWidth() / 2) - ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext) / 2;
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
                mPaint.setAlpha(125);
                mPaint.setStyle(Paint.Style.FILL);
                radius = (getWidth() / 2) - ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
                break;
            case STATE_DOWN:
                //手按住
                mPaint.setColor(color);
                mPaint.setStrokeWidth(ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setAntiAlias(true);
                radius = (getWidth() / 2) - ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext) / 2;
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);

                mPaint.setStyle(Paint.Style.FILL);
                radius = (getWidth() / 5);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
                break;
            case STATE_UP:
                //手松开
                mPaint.setColor(color);
                mPaint.setStrokeWidth(ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setAntiAlias(true);
                radius = (getWidth() / 2) - ScreenUtils.calculateDpToPx(PAINT_WIDTH, mContext) / 2;
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);

                mPaint.setStyle(Paint.Style.FILL);
                radius = (getWidth() / 5);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
                break;
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("spq","left"+(getLeft())+"  x:"+event.getX());
//        double length=Math.pow((event.getX()-getWidth()/2),2)+Math.pow((event.getY()-getHeight()/2),2)-Math.pow(radius,2);
//        if(length<=0){
//            setState(STATE_DOWN);
//        }
//        return true;
//    }

    public interface OnStateChangeListener {
        void onChange(int state, GesturePasswordView gesturePasswordView);
    }
}
