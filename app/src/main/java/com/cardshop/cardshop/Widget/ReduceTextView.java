package com.cardshop.cardshop.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class ReduceTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint paint;
    protected int HstartX, HstartY, HendX, HendY;//水平的线
    protected int paintWidth = 4;//初始化加号的粗细为10
    protected int paintColor = Color.parseColor("#9B9B9B");//画笔颜色黑色
    protected int padding = 15;//默认3的padding
    private int width, height;

    public ReduceTextView(Context context) {
        super(context);
        initView();
    }

    public ReduceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReduceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(paintWidth);
    }


    public int getPadding() {
        return padding;
    }

    //让外界调用，修改加号颜色
    public void setPaintColor(int paintColor) {
        paint.setColor(paintColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            //  MeasureSpec.EXACTLY表示该view设置的确切的数值
            width = widthSize;
        } else {
            width = 60;//默认值
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 60;
        }
        //这样做是因为加号宽高是相等的，手动设置宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //水平的横线
        canvas.drawRect(padding, (height-paintWidth)/2,width-padding , (height+paintWidth)/2, paint);
    }
}
