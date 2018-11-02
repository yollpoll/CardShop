package com.cardshop.framework.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtils {
    public static void loadImage(String url, ImageView imageView, Context context) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public static void loadCycleImage(String url, ImageView imageView, Context context) {
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
    }

    public static void loadCycleImage(String url, ImageView imageView, int placeHolder,Context context) {
        RequestOptions options=new RequestOptions().placeholder(placeHolder).circleCrop();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
