package com.zekers.ui.view.curtain;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zekers.ui.R;

import java.util.List;

/**
 * Created by Jun on 2016/9/15.
 */

public class CurtainHeaderView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "CurtainHeaderView";
    // 文字
    public TextView mTextView;

    // 指示箭头
    private ImageView mArrowView;

    private CurtainWindow mMenuView;

    public CurtainHeaderView(Context context) {
        super(context);
    }

    public CurtainHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private final Animation mShowAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.curtain_arrow_show);

    private final Animation mDissmissAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.curtain_arrow_hide);

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(mText);
        mArrowView = (ImageView) findViewById(R.id.imageView);
        mArrowView.setVisibility(mIndicatorShow?VISIBLE:GONE);
        this.setOnClickListener(this);

        // 动画结束，箭头向上图标
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 将图标倒过来
                mArrowView.setImageResource(R.drawable.arrow_up);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // 动画结束，恢复箭头向下图标
        mDissmissAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mArrowView.setImageResource(R.drawable.arrow_down);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mMenuView = new CurtainWindow(getContext(), new CurtainWindow.Callback() {
            @Override
            public void show() {
                if (mArrowView.getVisibility() == VISIBLE) {
                    mArrowView.startAnimation(mShowAnimation);
                }

            }

            @Override
            public void dissmiss() {
                if (mArrowView.getVisibility() == VISIBLE) {
                    mArrowView.startAnimation(mDissmissAnimation);
                }
            }
        });

        mMenuView.setData(mData);
        mMenuView.setOnItemClickListener(mOnItemClickListener);

        mMenuView.setAnchorView(mAnchorView == null?this:mAnchorView);

        if(mSelectedTextColor > 0) {
            mMenuView.setBackgroudColor(mSelectedTextColor);
        }
//        if (mSelectedIndex >= 0) {
//            mMenuView.setSelectedIndex(mSelectedIndex);
//        }

        post(new Runnable() {
            @Override
            public void run() {
                setSelectedIndex(mSelectedIndex>=0?mSelectedIndex:0);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (view == this) {
            mMenuView.show();
        }
    }

    private List<String> mData;

    public void setData(List<String> menus) {
        mData = menus;
        if (mMenuView != null) {
            mMenuView.setData(menus);
        }
    }

    private CurtainBoardView.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(CurtainBoardView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
        mMenuView.setOnItemClickListener(new CurtainBoardView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object data) {
                mSelectedIndex = position;
                mTextView.setText(data==null?null:data.toString());
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, position, data);
                }
            }
        });
    }

    private View mAnchorView;
    public void setAnchorView(View view) {
        mAnchorView = view;
        if (mMenuView != null) {
            mMenuView.setAnchorView(view);
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        if (mMenuView != null) {
            mMenuView.setOff(xOffset, yOffset);
        }
    }

    private String mText;

    public void setText(String text) {
        mText = text;
        if (mTextView != null) {
            mTextView.setText(text);
        }
    }

    private boolean mIndicatorShow = true;
    public void setIndicatorShown(boolean show) {
        mIndicatorShow = show;
        if (mArrowView != null) {
            mArrowView.setVisibility(show?VISIBLE:GONE);
        }
    }

    private int mSelectedTextColor = 0;

    // 选中的字体颜色
    public void setSelectedTextColor(int color) {
        mSelectedTextColor = color;
        if (mMenuView != null) {
            mMenuView.setSelectedTextColor(color);
        }
    }

    private int mSelectedIndex = -1;

    public void setSelectedIndex(int index) {
        mSelectedIndex = index;
        if (mMenuView != null) {
            mMenuView.setSelectedIndex(index);
        }
    }
}
