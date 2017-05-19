package com.example.payapp.view.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.payapp.R;
import com.example.payapp.model.Details;
import com.example.payapp.view.adapter.PayAdapter;
import com.example.payapp.view.adapter.PayTypeFactory;
import com.zekers.ui.view.recycler.VisitableTypeControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoker on 2017/3/2.
 */

public class PayFragment extends Fragment{
    RecyclerView list;
    PayAdapter adapter;
    List<VisitableTypeControl.Visitable> mdata;
    public static MyInfoFragment getInstance(){
        MyInfoFragment mainFragment=new MyInfoFragment();
        return mainFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list=(RecyclerView) inflater.inflate(R.layout.view_list,container,false);
        list.setLayoutManager(new LinearLayoutManager(container.getContext()));
        adapter=new PayAdapter(PayTypeFactory.getInstance());
        initData();
        adapter.setmDatas(mdata);
        list.setAdapter(adapter);
        return list;
    }
    public void initData(){
        mdata=new ArrayList<>();
        mdata.add(new Details());
        mdata.add(new Details());
        mdata.add(new Details());
        mdata.add(new Details());
        mdata.add(new Details());
        mdata.add(new Details());
        mdata.add(new Details());
    }
}
