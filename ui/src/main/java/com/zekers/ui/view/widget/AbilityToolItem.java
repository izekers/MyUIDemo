package com.zekers.ui.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zekers.ui.R;

/**
 *
 * Created by Zoker on 2017/5/5.
 */
public class AbilityToolItem extends FrameLayout {
    private TextView txt;
    private ImageView icon;
    private FrameLayout layout;
    private static final String TAG = AbilityToolItem.class.getSimpleName();
    public AbilityToolItem(Context context) {
        super(context);
        initView();
    }

    public AbilityToolItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AbilityToolItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbilityToolItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        txt=new TextView(getContext());
        icon=new ImageView(getContext());
        layout=new FrameLayout(getContext());

        Resources resources=getResources();

        LayoutParams txtLP=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        txtLP.gravity= Gravity.CENTER;
        txt.setVisibility(INVISIBLE);
        txt.setLayoutParams(txtLP);
        //COMPLEX_UNIT_PX 单位为px   COMPLEX_UNIT_SP 单位为sp
//        txt.setTextSize(resources.getDimension(R.dimen.toolbar_btn_txtsize));这个方法默认单位为sp
        txt.setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.toolbar_btn_txtsize));
        txt.setTextColor(resources.getColor(R.color.white));

        LayoutParams iconLP=new LayoutParams(resources.getDimensionPixelOffset(R.dimen.toolbar_btn_size),resources.getDimensionPixelOffset(R.dimen.toolbar_btn_size));
        iconLP.leftMargin=resources.getDimensionPixelOffset(R.dimen.toolbar_margin);
        iconLP.rightMargin=resources.getDimensionPixelOffset(R.dimen.toolbar_margin);
        iconLP.gravity= Gravity.CENTER;
        icon.setVisibility(INVISIBLE);
        icon.setLayoutParams(iconLP);

        LayoutParams layoutLP=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        layoutLP.gravity= Gravity.CENTER;
        layout.setVisibility(INVISIBLE);
        layout.setLayoutParams(layoutLP);

        layout.setBackgroundColor(Color.YELLOW);
        this.addView(txt);
        this.addView(icon);
        this.addView(layout);
    }

    public void setTextSize(float txtSize){
        Log.d(TAG,"#setTextSize txtSize");
        txt.setTextSize(txtSize);
    }
    public void setTextSize(int unit, float size){
        Log.d(TAG,"#setTextSize unit size");
        txt.setTextSize(unit,size);
    }
    public void setText(CharSequence text){
        Log.d(TAG,"#setText text");
        txt.setText(text);
        txt.setVisibility(VISIBLE);
        icon.setVisibility(INVISIBLE);
        layout.setVisibility(INVISIBLE);
    }

    /**
     * 设置返回图标
     * @param drawable
     */
    public void setIcon(Drawable drawable){
        Log.d(TAG,"#setIcon drawable");
        icon.setImageDrawable(drawable);
        icon.setVisibility(VISIBLE);
        layout.setVisibility(INVISIBLE);
        txt.setVisibility(INVISIBLE);
    }

    /**
     * 设置返回图标
     * @param iconRes
     */
    public void setIcon(@DrawableRes int iconRes){
        Log.d(TAG,"#setIcon iconRes");
        icon.setImageResource(iconRes);
        icon.setVisibility(VISIBLE);
        layout.setVisibility(INVISIBLE);
        txt.setVisibility(INVISIBLE);
    }

    public void setView(View view){
        Log.d(TAG,"#setView view");
        layout.removeAllViews();
        layout.addView(view);
        layout.setVisibility(VISIBLE);
        icon.setVisibility(INVISIBLE);
        txt.setVisibility(INVISIBLE);
    }

    public void setView(@LayoutRes int layoutRes){
        Log.d(TAG,"#setView layoutRes");
        View view=LayoutInflater.from(getContext()).inflate(layoutRes,layout,false);
        layout.removeAllViews();
        layout.addView(view);
        layout.setVisibility(VISIBLE);
        icon.setVisibility(INVISIBLE);
        txt.setVisibility(INVISIBLE);
    }

    public TextView getTxt() {
        Log.d(TAG,"#getTxt");
        return txt;
    }

    public ImageView getIcon() {
        Log.d(TAG,"#getIcon");
        return icon;
    }

    public FrameLayout getLayout() {
        Log.d(TAG,"#getLayout");
        return layout;
    }

    public void hide(){
        Log.d(TAG,"#hide");
        txt.setVisibility(INVISIBLE);
        icon.setVisibility(INVISIBLE);
        layout.setVisibility(INVISIBLE);
    }

    public void setConfig(Config config){
        Log.d(TAG,"#setConfig");
        if (config==null)
            return;
        if (config.listener!=null)
            this.setOnClickListener(config.listener);
        if (config.iconRes!=-1)
            this.setIcon(config.iconRes);
        if (config.icon!=null)
            this.setIcon(config.icon);
        if (config.text!=null)
            this.setText(config.text);
        if (config.view!=null)
            this.setView(config.view);
        if (config.layoutRes!=-1)
            this.setView(config.layoutRes);
        if (config.textsize!=-1)
            this.setTextSize(config.textsize);
    }

    public static class Config{
        OnClickListener listener=null;
        View view=null;
        @LayoutRes int layoutRes=-1;
        Drawable icon=null;
        @DrawableRes int iconRes=-1;
        CharSequence text=null;
        float textsize=-1;

        public Config setOnClickListener(OnClickListener listener){
            this.listener=listener;
            return this;
        }

        private void reset(){
            this.text=null;
            this.icon=null;
            this.iconRes=-1;
            this.layoutRes=-1;
            this.view=null;
        }

        public Config setText(CharSequence text){
            reset();
            this.text=text;
            return this;
        }

        public Config setView(View view){
            reset();
            this.view=view;
            return this;
        }
        public Config setView(@LayoutRes int layoutRes){
            reset();
            this.layoutRes=layoutRes;
            return this;
        }

        public Config setIcon(Drawable icon){
            reset();
            this.icon=icon;
            return this;
        }

        public Config setIcon(@DrawableRes int iconRes){
            reset();
            this.iconRes=iconRes;
            return this;
        }

        //单位sp
        public Config setTextSize(float textsize){
            this.textsize=textsize;
            return this;
        }
    }
}
