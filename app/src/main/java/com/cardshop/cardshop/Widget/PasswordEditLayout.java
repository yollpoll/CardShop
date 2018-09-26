package com.cardshop.cardshop.Widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cardshop.cardshop.R;
import com.cardshop.framework.Utils.ScreenUtils;

public class PasswordEditLayout extends LinearLayout {
    public static final int PSWNUM = 6;
    public static final int KEY_BACK = 67;
    public static final int KEY_OFFSET = 7;//键盘输入和接收事件的数字差
    private int inputSite = 0;//当前输入到的位置
    private OnInputCompleteListener onInputCompleteListener;
    private String psw = "";

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
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        setOrientation(HORIZONTAL);
        int padding = ScreenUtils.calculateDpToPx(0.5, getContext());
        setPadding(padding, padding, padding, padding);
        initChild();
    }

    private void initChild() {
        if (0 != getChildCount())
            return;
        for (int i = 0; i < PSWNUM; i++) {
            TextView tvPsw = new TextView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ScreenUtils.calculateDpToPx(54, getContext()),
                    ScreenUtils.calculateDpToPx(54, getContext()));
            tvPsw.setLayoutParams(params);
            tvPsw.setGravity(Gravity.CENTER);
            tvPsw.setTextSize(15);
            tvPsw.setBackground(getContext().getResources().getDrawable(R.drawable.shape_paypassword));
            tvPsw.setId(i);
            this.addView(tvPsw);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("spq","keyCode>>>>>>>>"+keyCode);
        inputPassword(keyCode - KEY_OFFSET);
        return super.onKeyDown(keyCode, event);
    }

    /**
     * @param key 输入数字
     */
    private void inputPassword(int key) {
        if (0 == getChildCount())
            return;
        if (key == KEY_BACK - KEY_OFFSET) {
            if (psw.length() > 0) {
                inputSite--;
                //删除一位
                TextView tvPsw = (TextView) getChildAt(inputSite);
                tvPsw.setText("");
                if (inputSite != 0) {
                    psw = psw.substring(0, inputSite);
                } else {
                    psw = "";
                }
            }
        } else if (key <= 9 && key >= 0) {
            if (inputSite == 6)
                return;
            TextView tvPsw = (TextView) getChildAt(inputSite);
            tvPsw.setText(key + "");
            inputSite++;
            psw += key;
            if (inputSite == 6 && null != onInputCompleteListener) {
                onInputCompleteListener.onComplete(psw);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("spq", "action" + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                InputMethodManager imm = (InputMethodManager) getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (null != imm) {
                    Log.d("spq", "show>>>>>>>>>>>");
                    imm.showSoftInput(this, 0);
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public interface OnInputCompleteListener {
        void onComplete(String psw);
    }

    public void clearPsw() {
        for (int i = 0; i < getChildCount(); i++) {
            TextView tvPsw = (TextView) getChildAt(i);
            tvPsw.setText("");
        }
        psw = "";
        inputSite = 0;
        postInvalidate();
    }
}
