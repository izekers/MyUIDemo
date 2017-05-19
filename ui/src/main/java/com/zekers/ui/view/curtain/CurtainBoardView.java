package com.zekers.ui.view.curtain;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zekers.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2016/3/17.
 */
public class CurtainBoardView extends LinearLayout {

    private static final String TAG = "CurtainBoardView";

    private List<CurtainBean> mData = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    // 字体颜色
    private int mTextColor = 0xFF000000;

    // 选中的字体颜色
    private int mSelectedTextColor = mTextColor;

    // 选中序号
    private int mSelectedIndex = -1;

    public CurtainBoardView(Context context) {
        super(context);
    }

    public CurtainBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurtainBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBeanData(List<CurtainBean> datas) {
        if (datas == null) {
            return;
        }

//        mData.clear();
        mData =  datas;

        removeAllViews();
        Log.i(TAG, "#setBeanData size = " + mData.size() + " and datas.size = " + datas.size());
        for (int i = 0, size = mData.size(); i < size; i++) {
            CurtainBean bean = mData.get(i);
//            String str = mData.get(i).getText();
            View view = View.inflate(getContext(), R.layout.curtain_item_view, null);

            // 文字
            TextView tv = (TextView)view.findViewById(R.id.dropdown_menu_item);
            tv.setText(bean.getText());
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(mTextColor);
            // 图标
            ImageView iv = (ImageView)view.findViewById(R.id.curtain_item_icon);
            iv.setVisibility(GONE);
            if (bean.getIcon() != null) {
                iv.setImageDrawable(bean.getIcon());
                iv.setVisibility(VISIBLE);
                tv.setGravity(Gravity.CENTER_VERTICAL);
            }

            addView(view);
        }
        initItemClickListener();
        update();
        invalidate();
    }

    public void setData(List<String> data) {

        if (data == null) {
            return;
        }

        List<CurtainBean> datas = new ArrayList<>();

        for (String d:data) {
            CurtainBean cb = new CurtainBean();
            cb.setText(d);
            datas.add(cb);
        }
        setBeanData(datas);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        initItemClickListener();
    }

    /**
     * 初始化
     */
    private void initItemClickListener() {
        Log.i(TAG, "#initItemClickListener getChildCount() = " + getChildCount());
        for (int i = 0, count = getChildCount(); i < count; i++) {

            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        int position = indexOfChild(v);
                        CurtainBean bean = mData.get(position);
                        Log.i(TAG, "#setOnItemClickListener$View.OnClickListener#onClick position = " + position + " and bean = " + (TextUtils.isEmpty(bean.getId())?bean.getText():bean));
                        mOnItemClickListener.onItemClick(v, position, TextUtils.isEmpty(bean.getId())?bean.getText():bean);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Object data);
    }

    public void setItemTextColor(@ColorInt int color) {
        mTextColor = color;
        update();
    }

    public void setSelectedTextColor(int color) {
        mSelectedTextColor = color;
        update();
    }

    public void setSelectedIndex(int index) {
        mSelectedIndex = index;
        update();
        if (mOnItemClickListener != null) {
            View v = getChildAt(index);
            if (v == null) {
                return;
            }
//            int position = indexOfChild(v);
            CurtainBean bean = mData.get(index);
            if (bean != null) {
                mOnItemClickListener.onItemClick(v, index, TextUtils.isEmpty(bean.getId())?bean.getText():bean);
            }
//            Log.i(TAG, "#setOnItemClickListener$View.OnClickListener#onClick position = " + position + " and bean = " + (TextUtils.isEmpty(bean.getId())?bean.getText():bean));

        }
    }

    private void update() {
        int count = mData == null?0:mData.size();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child instanceof TextView) {
                TextView tv = (TextView)child.findViewById(R.id.dropdown_menu_item);
                tv.setTextColor(mSelectedIndex == i?mSelectedTextColor:mTextColor);
            }
        }
    }
}
