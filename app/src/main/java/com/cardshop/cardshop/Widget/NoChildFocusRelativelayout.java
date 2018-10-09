package com.cardshop.cardshop.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 鹏祺 on 2016/10/11.
 */

public class NoChildFocusRelativelayout extends RelativeLayout {
    public NoChildFocusRelativelayout(Context context) {
        super(context);
    }

    public NoChildFocusRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoChildFocusRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
