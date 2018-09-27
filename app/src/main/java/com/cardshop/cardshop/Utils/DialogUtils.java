package com.cardshop.cardshop.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ForgetPasswordActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.framework.Utils.ScreenUtils;

/**
 * Created by 鹏祺 on 2018/4/3.
 */

public class DialogUtils {
    /**
     * 生成一个Alertdialog
     *
     * @param context
     * @param layoutId
     * @return
     */
    public static AlertDialog showAlertDialog(Context context, int layoutId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static Dialog showDialog(Context context, int layoutId) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        dialog.setContentView(view);
        dialog.show();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return dialog;
    }

    /**
     * 根据参数设置dialog宽高
     *
     * @param context
     * @param dialog
     * @param width
     * @param height
     */
    public static void setDialog(Activity context, Dialog dialog, int width, int height) {
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        window.setAttributes(layoutParams);
    }

    /**
     * @param context
     * @param dialog
     */
    public static void setDialogWrapContent(Activity context, Dialog dialog) {
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Log.d("spq", display.getHeight() + "...");
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }

    public static Dialog showPayPasswordDialog(final Activity context,
                                             PasswordEditLayout.OnInputCompleteListener onInputCompleteListener) {
        Dialog dialog = DialogUtils.showDialog(context, R.layout.layout_dialog_pay_password);
        DialogUtils.setDialog(context, dialog, ScreenUtils.calculateDpToPx(370, context)
                , ScreenUtils.calculateDpToPx(215, context));
        PasswordEditLayout passwordEditLayout = dialog.findViewById(R.id.ll_password);
        passwordEditLayout.setOnInputCompleteListener(onInputCompleteListener);
        TextView tvChangePsw = dialog.findViewById(R.id.tv_forgetpswd);
        tvChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.gotoForgetPasswordActivity(context, "忘记密码");
            }
        });
        return dialog;
    }
}
