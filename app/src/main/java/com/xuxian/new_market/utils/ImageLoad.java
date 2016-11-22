package com.xuxian.new_market.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by youarenotin on 2016/11/6.
 */

public class ImageLoad {
    public  static void loadImage(Context context , String url, ImageView view, int defId, int failedId, DiskCacheStrategy strategy) {
        if (url.isEmpty())
            return;
        Glide.with(context).load(url).error(failedId).placeholder(defId).diskCacheStrategy(strategy).into(view);
    }

    public static void loadImage(Context context, String url, ImageView view, DiskCacheStrategy diskCacheStrategy) {
        if (url.isEmpty())
            return;
        Glide.with(context).load(url).diskCacheStrategy(diskCacheStrategy).into(view);
    }
}
