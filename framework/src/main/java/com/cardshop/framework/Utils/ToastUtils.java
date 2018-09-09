package com.cardshop.framework.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.cardshop.framework.Base.SpqApplication;

/**
 * Created by xlm on 2016/1/15.
 */
public class ToastUtils {
    public static void showShort(String content){
        Toast.makeText(SpqApplication.getInstance().getApplicationContext(),content, Toast.LENGTH_SHORT).show();
    }
    public static void SnackerShowShort(View view, String content){
        Snackbar.make(view,content,Snackbar.LENGTH_SHORT).show();
    }
}
