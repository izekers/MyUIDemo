package com.zekers.ui.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by Zoker on 2017/3/3.
 */

public class UIImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        imageView.setImageDrawable(defaultDrawable);
    }

    @Override
    public void clearMemoryCache() {

    }
}
