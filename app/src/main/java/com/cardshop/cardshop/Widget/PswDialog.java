package com.cardshop.cardshop.Widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class PswDialog extends Dialog {
    public PswDialog(@NonNull Context context) {
        super(context);
    }

    public PswDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PswDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        View view = getCurrentFocus();
        if(view instanceof TextView){
            InputMethodManager mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
        super.dismiss();

    }
}
