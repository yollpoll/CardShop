package com.cardshop.framework.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {
    public static void loadImage(String url, ImageView imageView, Context context) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
