package com.cardshop.framework.Utils;

import android.content.Context;

public class ScreenUtils {
    public static int calculateDpToPx(double padding_in_dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (padding_in_dp * scale + 0.5f);
    }
}
