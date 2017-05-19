package com.zekers.viewPager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 最基本的PagerAdapter的实现
 * Created by zekers on 2016/10/20.
 */

public class BasePagerAdapter extends PagerAdapter{
    private List<View> views;

    public BasePagerAdapter(List<View> views){
        this.views=views;
    }
    //返回要滑动的VIew的个数
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //从当前container中删除指定位置（position）的View;
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(views.get(position));
    }

    //做了两件事，第一：将当前视图添加到container中，第二：返回当前View
    // 在每次ViewPager需要一个用以显示的Object的时候，该函数都会被 ViewPager.addNewItem() 调用。
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
}
