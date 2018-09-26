package com.cardshop.cardshop.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cardshop.cardshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 鹏祺 on 2017/2/8.
 */

public class GesturePasswodLayout extends RelativeLayout {
    private Context mContext;

    public GesturePasswodLayout(Context context) {
        super(context);
        init(context);
    }

    public GesturePasswodLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GesturePasswodLayout);
        lineColor = a.getColor(R.styleable.GesturePasswodLayout_line_color, 0x000000);
        gestureViewClolor = a.getColor(R.styleable.GesturePasswodLayout_gesture_view_color, 0x000000);
        verticalNum = a.getInteger(R.styleable.GesturePasswodLayout_vertical_number, 3);
        horizontalNum = a.getInteger(R.styleable.GesturePasswodLayout_horizontal_number, 3);
        a.recycle();
        init(context);
    }

    public GesturePasswodLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //最近触摸的点
    private boolean isSetMode = false;//设置模式
    private float lastX, lastY, touchX, touchY;
    private int lineColor, gestureViewClolor;
    private Path mPath;
    private Paint mPaint;
    private int verticalNum, horizontalNum;
    private int mPaintAlpha = 125;
    private List<GesturePasswordView> listGestureViews = new ArrayList<>();
    private List<GesturePasswordView> listPasswordViews = new ArrayList<>();
    private OnGesturePosswordChangeListener onGesturePosswordChangeListener;

    public void setIsSetMode(boolean mode) {
        this.isSetMode = mode;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initGestureView(mContext);
        int count = getChildCount();
        int lengthX = l, lengthY = t;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();

            int verticalNo = i % verticalNum;//列数
            int horNo = i / verticalNum;//行数

            int left = verticalNo * (width + (getWidth() - width * horizontalNum) / (horizontalNum - 1));
            int top = horNo * (height + (getHeight() - height * verticalNum) / (verticalNum - 1));
            int right = left + width;
            int bottom = top + height;

            child.layout(left, top, right, bottom);
        }
    }

    //ViewGroup 一般重写这个draw,这个是分发子view的draw时调用
    //onDraw在没有子view时不会调用
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.requestLayout();
        }
        if (isTouchEventUp) {
            //抬手时，要吧多余的线段清除掉
            for (int i = 0; i < listPasswordViews.size(); i++) {
                GesturePasswordView item = listPasswordViews.get(i);
                if (i == 0) {
                    mPath.moveTo(item.getCenterX(), item.getCenterY());
                } else {
                    mPath.lineTo(item.getCenterX(), item.getCenterY());
                }
            }
            if (isSetMode)
                mPath.reset();
            canvas.drawPath(mPath, mPaint);
        } else {
            if (isCleanCanvas) {
                return;
            }
            if (!(touchX == 0 && touchY == 0)) {
                GesturePasswordView gesturePasswordView = getGestureViewByPos(lastX, lastY);
                if (null != gesturePasswordView) {
                    canvas.drawLine(gesturePasswordView.getCenterX(), gesturePasswordView.getCenterY(), touchX, touchY, mPaint);
                    canvas.drawPath(mPath, mPaint);
                } else {
                    canvas.drawLine(lastX, lastY, touchX, touchY, mPaint);
                }
            }
        }
    }

    private void init(Context context) {
        mContext = context;
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setAlpha(mPaintAlpha);
    }

    private void initGestureView(Context context) {
        if (getChildCount() != 0)
            return;
        for (int i = 0; i < horizontalNum; i++) {
            for (int j = 0; j < verticalNum; j++) {
                GesturePasswordView gesturePasswordView = new GesturePasswordView(context);
                gesturePasswordView.setColor(gestureViewClolor);
                gesturePasswordView.setVisibility(VISIBLE);
                gesturePasswordView.setOnStateChangeListener(onStateChangeListener);
                gesturePasswordView.setId(i * verticalNum + j);//添加序号
                listGestureViews.add(gesturePasswordView);
                int radius;
                if (horizontalNum > verticalNum) {
                    //行数大于列数
                    radius = getHeight() / ((horizontalNum * 2) - 1);
                } else {
                    radius = getWidth() / ((verticalNum * 2) - 1);
                }
                mPaint.setStrokeWidth(radius / 5);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(radius, radius);
                gesturePasswordView.setLayoutParams(layoutParams);
                this.addView(gesturePasswordView);
            }
        }
    }

    private GesturePasswordView.OnStateChangeListener onStateChangeListener = new GesturePasswordView.OnStateChangeListener() {
        @Override
        public void onChange(int state, GesturePasswordView gesturePasswordView) {
            listPasswordViews.add(gesturePasswordView);
        }
    };

    public void setOnGesturePosswordChangeListener(OnGesturePosswordChangeListener onGesturePosswordChangeListener) {
        this.onGesturePosswordChangeListener = onGesturePosswordChangeListener;
    }

    public void onCheckEvent(boolean isVerified) {
        if (isVerified) {
        } else {
        }
    }

    /**
     * 清除密码
     */
    private void clearPassword() {
        for (GesturePasswordView gesturePasswordView : listGestureViews) {
            gesturePasswordView.setState(GesturePasswordView.STATE_NORMAL);
        }
        listPasswordViews.clear();
    }

    /**
     * 查看当前坐标是否在view中
     *
     * @return
     */
    private boolean checkPositionInChild(View view, float x, float y) {
        int padding = (int) (view.getWidth() * 0.1);
        if (x >= view.getLeft() + padding && x <= view.getRight() - padding
                && y >= view.getTop() + padding && y <= view.getBottom() - padding) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过坐标找到view
     *
     * @param x
     * @param y
     * @return
     */
    private GesturePasswordView getGestureViewByPos(float x, float y) {
        Log.i("spq", "move x " + x + "move y " + y);
        for (GesturePasswordView gesturePasswordView : listGestureViews) {
            if (checkPositionInChild(gesturePasswordView, x, y)) {
                return gesturePasswordView;
            }
        }
        return null;
    }

    private boolean isTouchEventUp = false;
    private boolean isCleanCanvas = false;//是否清除画布

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                clearPassword();
                isTouchEventUp = false;
                mPath.reset();
                lastX = event.getX();
                lastY = event.getY();
                touchX = event.getX();
                touchY = event.getY();
                GesturePasswordView downView = getGestureViewByPos(lastX, lastY);
                if (null != downView) {
                    //按下去的时候是在一个view中
                    downView.setState(GesturePasswordView.STATE_DOWN);
                    mPath.moveTo(downView.getCenterX(), downView.getCenterY());
                    isCleanCanvas = false;
                } else {
                    isCleanCanvas = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                GesturePasswordView moveView = getGestureViewByPos(touchX, touchY);
                if (null != moveView && moveView.getState() == GesturePasswordView.STATE_NORMAL) {
                    isCleanCanvas = false;
                    moveView.setState(GesturePasswordView.STATE_DOWN);
                    lastX = moveView.getCenterX();
                    lastY = moveView.getCenterY();
                    if (listPasswordViews.size() != 1) {
                        mPath.lineTo(lastX, lastY);
                    }
                    mPath.moveTo(lastX, lastY);
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouchEventUp = true;
                if (null != onGesturePosswordChangeListener) {
                    onGesturePosswordChangeListener.onCheck(listPasswordViews);
                    if (isSetMode)
                        clearPassword();
                }
                break;
        }
        invalidate();
        return true;
    }

    public interface OnGesturePosswordChangeListener {
        void onCheck(List<GesturePasswordView> listPassword);
    }
}
