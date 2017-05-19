package com.example.payapp.view.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.payapp.R;
import com.example.payapp.view.Constant;
import com.example.payapp.view.frament.PayFragment;
import com.zekers.ui.view.widget.SimpleFragmentPagerAdapter;

/**
 * Created by Zoker on 2017/3/2.
 */

public class PayActivity extends BaseActivity{
    TabLayout tab;
    ViewPager vp;
    SimpleFragmentPagerAdapter adapter;

    private Constant.Pay_Type type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        type=(Constant.Pay_Type)getIntent().getSerializableExtra(Constant.PAY_TYPE);
        initView();
    }

    public void initView(){
        vp=(ViewPager)findViewById(R.id.pay_vp);
        tab=(TabLayout)findViewById(R.id.pay_tab);
        adapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        initFragment(adapter);
        tab.setupWithViewPager(vp);
    }

    public void initFragment(SimpleFragmentPagerAdapter adapter){
        adapter.addFragment(PayFragment.getInstance(),getString(R.string.collection_order_all));
        adapter.addFragment(PayFragment.getInstance(),getString(R.string.collection_order_wait));
        adapter.addFragment(PayFragment.getInstance(),getString(R.string.collection_order_has));
    }
}
