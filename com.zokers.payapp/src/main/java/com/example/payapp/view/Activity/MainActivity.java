package com.example.payapp.view.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.payapp.R;
import com.example.payapp.view.frament.AddBookFragment;
import com.example.payapp.view.frament.MainFragment;
import com.example.payapp.view.frament.MyInfoFragment;
import com.example.payapp.view.frament.RecordsFragment;
import com.zekers.ui.view.widget.SimpleFragmentPagerAdapter;

public class MainActivity extends BaseActivity {
    BottomNavigationBar mBottomNavigationBar;
    ViewPager vp;
    SimpleFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }
    private void initView(){
        vp=(ViewPager)findViewById(R.id.main_vp);
        adapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        initFragment(adapter);
        vp.setAdapter(adapter);

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_menu, R.string.home))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu, R.string.transaction_records))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu, R.string.the_address_book))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu, R.string.my))//依次添加item,分别icon和名称
                .setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化
    }

    public void initFragment(SimpleFragmentPagerAdapter adapter){
        adapter.addFragment(MainFragment.getInstance(),"主页");
        adapter.addFragment(RecordsFragment.getInstance(),"订单");
        adapter.addFragment(AddBookFragment.getInstance(),"通信录");
        adapter.addFragment(MyInfoFragment.getInstance(),"我的");
    }

    public void initListener(){
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
