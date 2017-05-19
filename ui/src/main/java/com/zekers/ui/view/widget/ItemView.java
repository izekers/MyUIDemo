package com.zekers.ui.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zekers.ui.R;

/**
 *
 * Created by Zoker on 2017/3/1.
 */
public class ItemView extends LinearLayout{
    private ImageView iconView,rightView;
    private TextView nameView;
    public ItemView(Context context) {
        super(context);
        initView();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        setData(attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        setData(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        setData(attrs);
    }
    public void initView(){
        this.setGravity(Gravity.CENTER);
        this.setOrientation(HORIZONTAL);
        iconView=new ImageView(getContext());
        iconView.setLayoutParams(new LayoutParams(getContext().getResources().getDimensionPixelOffset(R.dimen.itemview_icon_width),getContext().getResources().getDimensionPixelOffset(R.dimen.itemview_icon_height)));

        rightView=new ImageView(getContext());
        rightView.setLayoutParams(new LayoutParams(getContext().getResources().getDimensionPixelOffset(R.dimen.itemview_right_width),getContext().getResources().getDimensionPixelOffset(R.dimen.itemview_right_height)));

        nameView=new TextView(getContext());
        nameView.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.itemview_name_textsize));
        nameView.setTextColor(getContext().getResources().getColor(R.color.itemview_name_textcolor));
        LinearLayout.LayoutParams nameParams=new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        nameParams.leftMargin=getContext().getResources().getDimensionPixelOffset(R.dimen.itemview_name_marginleft);
        nameView.setLayoutParams(nameParams);
        this.addView(iconView);
        this.addView(nameView);
        this.addView(rightView);
    }

    public void setData(AttributeSet attrs){
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable.itemView);
        String name=typedArray.getString(R.styleable.itemView_name);
        name=name!=null ? name:"name";
        int nameid=typedArray.getResourceId(R.styleable.itemView_name,0);
        int icon=typedArray.getResourceId(R.styleable.itemView_lefticon,0);
        int right=typedArray.getResourceId(R.styleable.itemView_right,0);
        if (nameid!=0)
            name=getContext().getResources().getString(nameid);
        nameView.setText(name);
        if (icon==0)
            iconView.setVisibility(GONE);
        else
            iconView.setImageDrawable(getContext().getResources().getDrawable(icon));
        if (right==0)
            rightView.setBackgroundColor(Color.GREEN);
        else
            rightView.setImageDrawable(getContext().getResources().getDrawable(right));

        typedArray.recycle();
    }

    public ImageView getIconView() {
        return iconView;
    }

    public ImageView getRightView() {
        return rightView;
    }

    public TextView getNameView() {
        return nameView;
    }
}
