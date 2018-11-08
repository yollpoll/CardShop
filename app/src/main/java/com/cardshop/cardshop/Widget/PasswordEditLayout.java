package com.cardshop.cardshop.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.LogUtil;
import com.cardshop.framework.Utils.ScreenUtils;

public class PasswordEditLayout extends AppCompatEditText {
    public static final int PSWNUM = 6;
    public static final int KEY_BACK = 67;
    public static final int KEY_OFFSET = 7;//键盘输入和接收事件的数字差
    public static final int PSW_SIZE_DP = 45;
    private int inputSite = 0;//当前输入到的位置
    private OnInputCompleteListener onInputCompleteListener;
    private String psw = "";
    private Paint mPaint, pswPaint;
    private float pswSize;

    public PasswordEditLayout(Context context) {
        super(context);
        init();
    }

    public PasswordEditLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnInputCompleteListener(OnInputCompleteListener onInputCompleteListener) {
        this.onInputCompleteListener = onInputCompleteListener;
    }

    private void init() {
        //接受输入内容必须
        setBackground(getContext().getResources().getDrawable(R.drawable.shape_paypassword));
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        requestFocus();

        setCursorVisible(false);
        setClickable(true);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(PSWNUM)});
        initPaint();
//        setOrientation(HORIZONTAL);
        pswSize = ScreenUtils.calculateDpToPx(PSW_SIZE_DP, getContext());
        int padding = ScreenUtils.calculateDpToPx(0.5, getContext());
        setPadding(padding, padding, padding, padding);
        initChild();
        setWidth((int) (pswSize*6));
        setHeight((int) pswSize);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(ScreenUtils.calculateDpToPx(0.5, getContext()));
        mPaint.setColor(getContext().getResources().getColor(R.color.colorLine));
        mPaint.setTextSize(ScreenUtils.sp2px(getContext(), 16));

        pswPaint = new Paint();
        pswPaint.setColor(getContext().getResources().getColor(R.color.colorText));
        pswPaint.setTextSize(ScreenUtils.sp2px(getContext(), 16));
    }

    private void initChild() {
//        if (0 != getChildCount())
//            return;
//        for (int i = 0; i < PSWNUM; i++) {
//            TextView tvPsw = new TextView(getContext());
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ScreenUtils.calculateDpToPx(45, getContext()),
//                    ScreenUtils.calculateDpToPx(45, getContext()));
//            tvPsw.setLayoutParams(params);
//            tvPsw.setGravity(Gravity.CENTER);
//            tvPsw.setFocusable(true);
//            tvPsw.setFocusableInTouchMode(true);
//            tvPsw.setTextSize(15);
//            tvPsw.setBackground(getContext().getResources().getDrawable(R.drawable.shape_paypassword));
//            tvPsw.setId(i);
//            this.addView(tvPsw);
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for (int i = 1; i <= 5; i++) {
            float x = pswSize * i;
            canvas.drawLine(x, 0, x, pswSize, mPaint);
        }
        String content = getText().toString();
        for (int i = 0; i < content.length(); i++) {
            LogUtil.Log("spq char>>>" + String.valueOf(content.charAt(i)));
            Rect rect = new Rect();
            mPaint.getTextBounds(String.valueOf(content.charAt(i)), 0, 1, rect);
            float heightOffset = (rect.height() / 2) - rect.bottom;
            float widthOffset = -(rect.left + (rect.width() / 2));
            if (i == psw.length() - 1) {
                canvas.drawText(String.valueOf(content.charAt(i)), (pswSize * i + (pswSize) / 2) + widthOffset, (pswSize / 2) + heightOffset, pswPaint);
            } else {
                canvas.drawCircle((pswSize * i + (pswSize) / 2), (pswSize / 2), 10, pswPaint);
            }
        }
    }


    //这个方法拦截输入拦截不到删除按钮
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
//        return super.onCreateInputConnection(outAttrs);
        return new InputConnectionWrapper(super.onCreateInputConnection(outAttrs), true) {
            @Override
            public boolean commitText(CharSequence text, int newCursorPosition) {
                LogUtil.Log("text>>>>>>>>>>>>>>>>>>>>" + text);
                psw += text;
                inputSite += newCursorPosition;
                setText(psw);
                if (inputSite == 6 && null != onInputCompleteListener) {
                    onInputCompleteListener.onComplete(psw);
                }
                return true;
//                return super.commitText(text, newCursorPosition);
            }

            @Override
            public boolean sendKeyEvent(KeyEvent event) {
                inputPassword(event.getKeyCode() - KEY_OFFSET);
                return true;
            }
        };
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.d("spq", "keyCode>>>>>>>>" + keyCode);
//        inputPassword(keyCode - KEY_OFFSET);
////        return super.onKeyDown(keyCode, event);
//        return true;
//    }

    /**
     * @param key 输入数字
     */
    private void inputPassword(int key) {
        if (key == KEY_BACK - KEY_OFFSET) {
            if (psw.length() > 0) {
                LogUtil.Log("del>>>>>>>>>>>>>>>>>>");
                inputSite--;
                //删除一位
                if (inputSite != 0) {
                    psw = psw.substring(0, inputSite);
                } else {
                    psw = "";
                }
            }
        }
//        else if (key <= 9 && key >= 0) {
//            if (inputSite == 6)
//                return;
//            inputSite++;
//            psw += key;
//            if (inputSite == 6 && null != onInputCompleteListener) {
//                onInputCompleteListener.onComplete(psw);
//            }
//        }
        setText(psw);
//        invalidate();
    }


    public interface OnInputCompleteListener {
        void onComplete(String psw);
    }

    public void clearPsw() {
        psw = "";
        inputSite = 0;
        postInvalidate();
    }

    public boolean checkInput() {
        if (psw.length() == PSWNUM) {
            return true;
        } else {
            return false;
        }
    }


    public String getPsw() {
        return psw;
    }

}
