package com.zekers.myuidemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class DemoActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        initView();
    }
    private void initView(){
//        ViewPager backPager=(ViewPager) findViewById(R.id.BackPager);
//        backPager.setAdapter(new BasePagerAdapter(initDemoView1()));
    }

    private List<View> initDemoView1(){
        List<View> views=new ArrayList<>();
        List<Integer> innerImagers=new ArrayList<>();
        innerImagers.add(R.drawable.welcome_phone1);
        innerImagers.add(R.drawable.welcome_phone2);
        innerImagers.add(R.drawable.welcome_phone3);
        innerImagers.add(R.drawable.welcome_phone4);
        for (int i=0;i<4;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.test_layout,null);
            ImageView imageView=(ImageView) view.findViewById(R.id.inner_img);
            Picasso.with(this).load(innerImagers.get(i)).into(imageView);
            views.add(view);
        }
        return views;
    }
    private List<View> initDemoView2(){
        List<View> views=new ArrayList<>();
        for (int i=0;i<4;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.test_layout2,null);
            views.add(view);
        }
        return views;
    }
}
