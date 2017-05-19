package com.zekers.myuidemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zekers.ui.view.recycler.typeControl.Demo_Model;
import com.zekers.ui.view.recycler.typeControl.Demo_TypeFactory;
import com.zekers.ui.view.recycler.typeControl.Visitable;
import com.zekers.ui.view.recycler.typeControl.VisitableAdapter;
import com.zekers.ui.view.recycler.typeControl.VisitableTypeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Zekers on 2017/1/12.
 */
public class CoolayoutActivity extends Activity{
    RecyclerView recyclerView;
    List<Visitable> mDatas=new ArrayList<>();
    Animation animation;
    VisitableTypeFactory typeFactory=new Demo_TypeFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coolayout);
        animation= AnimationUtils.loadAnimation(this,R.anim.jitter);
        recyclerView=(RecyclerView) findViewById(R.id.Rc);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        Demo_Model[] ones={new Demo_Model(),new Demo_Model(),new Demo_Model(),new Demo_Model(),new Demo_Model(),new Demo_Model(),new Demo_Model(),new Demo_Model()};
        for(int i=0;i<ones.length;i++)
        {
            mDatas.add(ones[i]);
        }

        recyclerView.setAdapter(new VisitableAdapter(typeFactory,mDatas));
    }
}
