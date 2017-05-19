package com.example.payapp.view.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.payapp.R;
import com.example.payapp.model.Details;
import com.example.payapp.model.Records;
import com.example.payapp.view.adapter.PayAdapter;
import com.example.payapp.view.adapter.PayTypeFactory;
import com.example.payapp.view.adapter.RecordsAdapter;
import com.zekers.ui.view.recycler.RecyclerWrapView;
import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.ui.view.widget.AbilityToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoker on 2017/3/2.
 */
public class RecordsFragment extends Fragment{
    View view;
    RecyclerWrapView recyclerWrapView;
    AbilityToolBar abilityToolBar;
//    RecordsAdapter adapter;
    RecordsAdapter adapter;
    List<Records> recordses;
   public static RecordsFragment getInstance(){
       RecordsFragment recordsFragment=new RecordsFragment();
       return recordsFragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_records,container,false);
        recyclerWrapView=(RecyclerWrapView)view.findViewById(R.id.rw_list);
        adapter=new RecordsAdapter(PayTypeFactory.getInstance());
        initData();
        adapter.setmDatas(recordses);
        recyclerWrapView.setAdapter(adapter);
        recyclerWrapView.setLayoutManager(new LinearLayoutManager(getContext()));
        abilityToolBar=(AbilityToolBar)view.findViewById(R.id.ability_toolbar);
        abilityToolBar.setTitle(getContext().getResources().getString(R.string.transaction_records));
        return view;
    }

    public void initData(){
        recordses=new ArrayList<>();
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
        recordses.add(new Records());
    }
}
