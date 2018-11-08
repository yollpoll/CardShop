package com.cardshop.cardshop.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cardshop.cardshop.R;
import com.cardshop.cardshop.View.Activity.ForgetPasswordActivity;
import com.cardshop.cardshop.Widget.PasswordEditLayout;
import com.cardshop.cardshop.Widget.PswDialog;


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

    public static Dialog showDialog(Context context, int layoutId, int style) {
        Dialog dialog = new Dialog(context, style);
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
                                               final PasswordEditLayout.OnInputCompleteListener onInputCompleteListener) {
        final PswDialog dialog = new PswDialog(context, R.style.PswDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_pay_password, null);
        dialog.setContentView(view);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        final PasswordEditLayout passwordEditLayout = dialog.findViewById(R.id.ll_password);
        passwordEditLayout.setOnInputCompleteListener(new PasswordEditLayout.OnInputCompleteListener() {
            @Override
            public void onComplete(String psw) {
                dialog.dismiss();
                onInputCompleteListener.onComplete(psw);
            }
        });
        TextView tvChangePsw = dialog.findViewById(R.id.tv_forgetpswd);
        passwordEditLayout.requestFocus();
        tvChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.gotoForgetPasswordActivity(context, "忘记密码");
            }
        });
        final Handler handler = new Handler();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showKeyboard(passwordEditLayout, context);
                    }
                }, 300);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    public static void showKeyboard(EditText editText, Context context) {
        //其中editText为dialog中的输入框的 EditText
        if (editText != null) {
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }

    public static void closeKeyBoard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
