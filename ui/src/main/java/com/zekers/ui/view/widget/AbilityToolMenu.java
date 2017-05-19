package com.zekers.ui.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zekers.ui.R;

/**
 * Created by Zoker on 2017/5/8.
 */

public class AbilityToolMenu extends FrameLayout {
    private static final String TAG = AbilityToolMenu.class.getSimpleName();
    private OnMenuItemSelectListener listener;

    public AbilityToolMenu(Context context) {
        super(context);
        initView();
    }

    public AbilityToolMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AbilityToolMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbilityToolMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }
    private void initView(){
        Resources resources=getResources();
        icon=new ImageView(getContext());

        LayoutParams iconLP=new LayoutParams(resources.getDimensionPixelOffset(R.dimen.toolbar_btn_size),resources.getDimensionPixelOffset(R.dimen.toolbar_btn_size));
        iconLP.leftMargin=resources.getDimensionPixelOffset(R.dimen.toolbar_margin);
        iconLP.rightMargin=resources.getDimensionPixelOffset(R.dimen.toolbar_margin);
        iconLP.gravity= Gravity.CENTER;
        icon.setLayoutParams(iconLP);
        this.addView(icon);
    }

    private ImageView icon;
    int mMenuRes;
    private PopupMenu mPopupMenu;
    PopupWindow mMenuPopup = new PopupWindow(getContext());

    /**
     * 设置返回图标
     * @param drawable
     */
    public void setIcon(Drawable drawable){
        Log.d(TAG,"#setIcon drawable");
        icon.setImageDrawable(drawable);
    }

    /**
     * 设置返回图标
     * @param iconRes
     */
    public void setIcon(@DrawableRes int iconRes){
        Log.d(TAG,"#setIcon iconRes");
        icon.setImageResource(iconRes);
    }

    public void setMenuRes(@MenuRes int menuRes){
        mMenuRes = menuRes;

        mPopupMenu = new PopupMenu(getContext(), this);
        mPopupMenu.inflate(mMenuRes);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenuPopup == null && mMenuPopup.isShowing()) {
                    mMenuPopup.dismiss();
                } else {
                    mMenuPopup = new PopupWindow(getContext());
                    LinearLayout menuView = (LinearLayout) View.inflate(getContext(), R.layout.menu, null);
                    if (mPopupMenu != null) {
                        Menu menu = mPopupMenu.getMenu();
                        for (int i = 0, size = menu.size(); i < size; i++) {
                            MenuItem menuItem = menu.getItem(i);
                            if (!menuItem.isVisible()|| !menuItem.isEnabled()){
                                continue;
                            }
                            View itemView = View.inflate(getContext(), R.layout.menu_item_view, null);
                            itemView.setId(menuItem.getItemId());
                            ImageView iv = (ImageView) itemView.findViewById(R.id.menu_item_icon);
                            if (menuItem.getIcon() != null) {
                                BitmapDrawable bd = (BitmapDrawable) menuItem.getIcon();
                                iv.setImageBitmap(bd.getBitmap());
                            }
                            iv.setVisibility(menuItem.getIcon() != null?VISIBLE:GONE);

                            TextView tv = (TextView) itemView.findViewById(R.id.menu_item_text);
                            tv.setText(menuItem.getTitle());
                            itemView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    Log.i(TAG, "$MenuSelected #onClick id = " + id);
                                    if (listener!=null){
                                        listener.onMenuItemClick(v,v.getId());
                                    }
                                }
                            });
                            menuView.addView(itemView);
                        }
                    }

                    menuView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
                    int width = menuView.getMeasuredWidth();
                    int height = menuView.getMeasuredHeight();
                    int[] anP = new int[2];
                    getLocationOnScreen(anP);
                    mMenuPopup.setContentView(menuView);
                    mMenuPopup.setWidth(width);
                    mMenuPopup.setHeight(height);
                    mMenuPopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
                    mMenuPopup.setOutsideTouchable(true);
                    mMenuPopup.setTouchable(true);
                    mMenuPopup.showAtLocation(AbilityToolMenu.this, Gravity.END|Gravity.TOP, 0,anP[1]+getMeasuredHeight());
                }
            }
        });
    }

    public void setOnMenuItemSelectListener(final OnMenuItemSelectListener listener){
        this.listener=listener;
    }

    public interface OnMenuItemSelectListener{
        void onMenuItemClick(View view,int id);
    }
}
