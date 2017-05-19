package com.zekers.ui.view.curtain;

import android.graphics.drawable.Drawable;

/**
 * Created by Jun on 2016/12/19.
 */

public class CurtainBean {

    // id
    private String id;

    // 图标
    private Drawable mIcon;

    // 文字
    private String text;

    // 是否可以用
    private boolean enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
