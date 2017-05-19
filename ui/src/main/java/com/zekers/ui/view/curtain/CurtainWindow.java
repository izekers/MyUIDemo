package com.zekers.ui.view.curtain;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.zekers.ui.R;

import java.util.List;

/**
 * Created by Jun on 2016/3/17.
 */
public class CurtainWindow {

    private static final String TAG = "CurtainWindow";

    private CurtainBoardView.OnItemClickListener mOnItemClickListener;

    private View mAnchorView;

    private int x;

    private int y;

    private Context mContext;

    private CurtainBoardView mContentView;

    private PopupWindow mPW;

    public CurtainWindow(Context context, Callback callback) {
        mContext = context;
        mCallback = callback;
        mContentView = (CurtainBoardView) View.inflate(context, R.layout.curtain, null);

        mPW = new PopupWindow(mContentView) {

            @Override
            public void showAsDropDown(View anchor, int x, int y) {
                super.showAsDropDown(anchor, x, y);
                if (mCallback != null) {
                    mCallback.show();
                }
            }

            @Override
            public void showAtLocation(View parent, int gravity, int x, int y) {
                super.showAtLocation(parent, gravity, x, y);
                if (mCallback != null) {
                    mCallback.show();
                }
            }
        };
        mPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mCallback != null) {
                    mCallback.dissmiss();
                }
            }
        });
        mPW.setContentView(mContentView);
        // 使其聚集
        mPW.setFocusable(true);
        // 设置允许在外点击消失
        mPW.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失
        mPW.setBackgroundDrawable(new BitmapDrawable());

    }

    public void setAnchorView(View view) {
        mAnchorView = view;
    }

    public void setOff(int xoff,int yoff) {
        x = xoff;
        y = yoff;
    }

    public void setData(List<String> data) {
        mContentView.setData(data);
    }

    public void setBeanData(List<CurtainBean> data) {
        mContentView.setBeanData(data);
    }

    public void setOnItemClickListener(CurtainBoardView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
//        mContentView.setOnItemClickListener(listener);
        mContentView.setOnItemClickListener(new CurtainBoardView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object data) {
                Log.i(TAG, "#setOnItemClickListener#CurtainBoardView.OnItemClickListener#onItemClick position = " + position + " and data = " + data + " and mOnItemClickListener = " + mOnItemClickListener);
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(view, position, data);
                }
                Log.i(TAG, "##setOnItemClickListener#CurtainBoardView.OnItemClickListener#onItemClick deal complete");
                mPW.dismiss();
                Log.i(TAG, "##setOnItemClickListener#CurtainBoardView.OnItemClickListener#onItemClick dismiss");
            }
        });

    }

    public void show() {
        showAtLocation(mAnchorView, x,y);
    }

    public void show(View anchor, int gravity) {
        int x = 0;
        int y = 0;
        if (gravity == Gravity.CENTER_HORIZONTAL) {
            mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int width = mContentView.getMeasuredWidth();
            x = anchor.getWidth()/2 - width /2;
        }

        showAtLocation(anchor, x, y);
    }

    public void showAtLocation(View anchor, int x, int y) {
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        int width = mContentView.getMeasuredWidth();
        int height = mContentView.getMeasuredHeight();
        mPW.setWidth(width);
        mPW.setHeight(height);

//        int[] location = new int[2];
//                anchor.getLocationOnScreen(location);
//        Log.i(TAG, "#showAtLocation lx = " + location[0] + " and ly = " + location[1]);
        if (mPW.isShowing()) {
            mPW.update();
        } else {
//            mPW.showAtLocation(anchor, Gravity.TOP, 0, location[1] + anchor.getMeasuredHeight());
            int xoffset = anchor.getMeasuredWidth()/2 - width/2 + x;
            Log.i(TAG, "#showAtLocation xoffset = " + xoffset);
            int anthH = anchor.getMeasuredHeight();
            int curtainViewH = height;//mContentView.getMeasuredHeight();
            Log.i(TAG, "#showAtLocation anthH = " + anthH + " and curtainViewH = " + curtainViewH);
            Log.i(TAG, "#showAtLocation xoffset = " + xoffset + " and y = " + y);
            mPW.showAsDropDown(anchor, xoffset, y);
        }
    }

    public void dissmiss() {
        mPW.dismiss();
    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {

        // 显示
        void show();

        // 消失
        void dissmiss();
    }

    // 选中的字体颜色
    public void setSelectedTextColor(int color) {
        if (mContentView != null) {
            mContentView.setSelectedTextColor(color);
        }
    }

    public void setItemTextColor(int color) {
        if (mContentView != null) {
            mContentView.setItemTextColor(color);
        }
    }

    public void setSelectedIndex(int index) {
        if (mContentView != null) {
            mContentView.setSelectedIndex(index);
        }
    }

    private @ColorInt
    int mBgColor = -1;

    public void setBackgroudColor(@ColorInt int color) {
        mBgColor = color;
        if (color > 0 && mContentView != null) {
            mContentView.setBackgroundDrawable(null);
            mContentView.setBackgroundColor(color);
        }
    }

    private Drawable mBg;

    public void setBackground(Drawable background) {
        mBg = background;
        if (mContentView != null) {
            mContentView.setBackgroundDrawable(mBg);
        }
    }

}
