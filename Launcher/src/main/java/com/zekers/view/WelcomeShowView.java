package com.zekers.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zekers.myuidemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * Created by zekers on 2016/10/19.
 */

public class WelcomeShowView extends FrameLayout{
    public WelcomeShowView(Context context) {
        super(context);
    }

    public WelcomeShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WelcomeShowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private ViewPager inner_vp;
    private ViewPager out_vp;
    private void init(){
        inner_vp=(ViewPager) this.findViewById(R.id.welcome_inner_vp);
        out_vp=(ViewPager) this.findViewById(R.id.welcome_out_vp);
        ArrayList<View> innerViewList=new ArrayList<>();
        for (int i=0;i<3;i++){
            ImageView imageView=new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }
        ArrayList<View> outViewList=new ArrayList<>();
    }

    private class InnerAdapter extends PagerAdapter{
        private ArrayList<View> innerViewlist;

        public InnerAdapter(ArrayList<View> views){
            this.innerViewlist=views;
        }

        @Override
        public int getCount() {
            return innerViewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object==view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(innerViewlist.get(position));
            return innerViewlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(innerViewlist.get(position));
        }
    }
}
