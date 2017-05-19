package com.zekers.ui.view.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zekers.ui.R;

/**
 * Created by Zoker on 2017/5/5.
 */

public class MyToolBar extends LinearLayout {
    private AbilityToolItem leftFirstItem, leftSecondItem, centerItem, rightFirstItem, rightSecondItem;
    private OnMenuItemClickListener onMenuItemClickListener;
    private MenuBuilder mMenu;
    private static final String TAG = MyToolBar.class.getSimpleName();

    public MyToolBar(Context context) {
        super(context);
        initView();
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        leftFirstItem = new AbilityToolItem(getContext());
        leftSecondItem = new AbilityToolItem(getContext());
        centerItem = new AbilityToolItem(getContext());
        rightSecondItem = new AbilityToolItem(getContext());
        rightFirstItem = new AbilityToolItem(getContext());

        LinearLayout.LayoutParams lfLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams lsLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams rsLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams rfLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        LinearLayout.LayoutParams lfLP = new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
//        LinearLayout.LayoutParams lsLP = new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
//        LinearLayout.LayoutParams rsLP = new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
//        LinearLayout.LayoutParams rfLP = new LayoutParams(0, LayoutParams.MATCH_PARENT,1);
        LinearLayout.LayoutParams cLp = new LayoutParams(0, LayoutParams.MATCH_PARENT, 3);

        leftFirstItem.setLayoutParams(lfLP);
        leftSecondItem.setLayoutParams(lsLP);
        rightSecondItem.setLayoutParams(rsLP);
        rightFirstItem.setLayoutParams(rfLP);
        centerItem.setLayoutParams(cLp);

        leftFirstItem.setIcon(R.drawable.toolbar_back);
        centerItem.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_title_txtsize));

        this.addView(leftFirstItem);
        this.addView(leftSecondItem);
        this.addView(centerItem);
        this.addView(rightSecondItem);
        this.addView(rightFirstItem);
    }

    public void setIconSize(int size) {
    }

    public void setTitle(String txt) {
        this.centerItem.setText(txt);
    }

    public void hideBackIcon() {
        leftFirstItem.hide();
    }

    /**
     * 设置返回图标
     *
     * @param drawable
     */
    public void setBackIcon(Drawable drawable) {
        leftFirstItem.setIcon(drawable);
    }

    /**
     * 设置返回图标
     *
     * @param backIconRes
     */
    public void setBackIcon(@DrawableRes int backIconRes) {
        leftFirstItem.setIcon(backIconRes);
    }

    /**
     * 设置返回文字
     *
     * @param backText
     */
    public void setBackText(String backText) {
        leftFirstItem.setText(backText);
    }

    /**
     * 设置返回按钮的点击事件
     *
     * @param listener
     */
    public void setOnBackClickListener(View.OnClickListener listener) {
        leftFirstItem.setOnClickListener(listener);
    }
    public void setMenuRes(@MenuRes int menuRes) {
        if (menuRes == 0) {
            return;
        }
        mMenu = new MenuBuilder(getContext());
        ((Activity) getContext()).getMenuInflater().inflate(menuRes, mMenu);

        Log.d(TAG, "#setMenuRes size=" + mMenu.size());
        rightFirstItem.hide();
        rightSecondItem.hide();
        for (int i = 0; i < mMenu.size(); i++) {
            MenuItem item = mMenu.getItem(i);
            AbilityToolItem toolItem = null;
            if (i == 0)
                toolItem = rightFirstItem;
            if (i == 1)
                toolItem = rightSecondItem;
            if (i == 2)
                break;

            if (toolItem != null) {
                toolItem.setId(item.getItemId());

                if (item.getTitle() != null)
                    toolItem.setText(item.getTitle());

                if (item.getIcon() != null)
                    toolItem.setIcon(item.getIcon());

                toolItem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onMenuItemClickListener != null)
                            onMenuItemClickListener.onMenuItemClick(v);
                    }
                });
            }
        }
    }

    public void setOnMenuClickListener(OnMenuItemClickListener listener) {
        this.onMenuItemClickListener = listener;
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(View view);
    }



    public static final int MODEL_SINGLE_TITLE = 895;
    public static final int MODEL_SINGLE_MENU = 231;
    public static final int MODEL_DOUBLE_MENU = 432;

    public void setModel(int model) {
        switch (model) {
            case MODEL_SINGLE_TITLE:
                leftFirstItem.setVisibility(GONE);
                leftSecondItem.setVisibility(GONE);
                rightSecondItem.setVisibility(GONE);
                rightFirstItem.setVisibility(GONE);
                break;
            case MODEL_SINGLE_MENU:
                leftFirstItem.setVisibility(VISIBLE);
                leftSecondItem.setVisibility(GONE);
                rightSecondItem.setVisibility(GONE);
                rightFirstItem.setVisibility(VISIBLE);
                break;
            case MODEL_DOUBLE_MENU:
                leftFirstItem.setVisibility(VISIBLE);
                leftSecondItem.setVisibility(VISIBLE);
                rightSecondItem.setVisibility(VISIBLE);
                rightFirstItem.setVisibility(VISIBLE);
                break;
        }
    }

    //主要被自定义调用
    public AbilityToolItem getLeftFirstItem() {
        return leftFirstItem;
    }

    public void setLeftFirstItem(AbilityToolItem.Config config) {
        leftFirstItem.setConfig(config);
    }

    public void hideLeftFirstItem() {
        leftFirstItem.setVisibility(INVISIBLE);
    }

    public AbilityToolItem getLeftSecondItem() {
        return leftSecondItem;
    }

    public void setLeftSecondItem(AbilityToolItem.Config config) {
        leftSecondItem.setConfig(config);
    }

    public void hideLeftSecondItem() {
        leftSecondItem.setVisibility(INVISIBLE);
    }

    public AbilityToolItem getCenterItem() {
        return centerItem;
    }

    public void setCenterItem(AbilityToolItem.Config config) {
        centerItem.setConfig(config);
    }

    public void hideCenterItem() {
        centerItem.setVisibility(INVISIBLE);
    }

    public AbilityToolItem getRightFirstItem() {
        return rightFirstItem;
    }

    public void setRightFirstItem(AbilityToolItem.Config config) {
        rightFirstItem.setConfig(config);
    }

    public void hideRightFirstItem() {
        rightFirstItem.setVisibility(INVISIBLE);
    }

    public AbilityToolItem getRightSecondItem() {
        return rightSecondItem;
    }

    public void setRightSecondItem(AbilityToolItem.Config config) {
        rightSecondItem.setConfig(config);
    }

    public void hideRightSecondItem() {
        rightSecondItem.setVisibility(INVISIBLE);
    }

}
