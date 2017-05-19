package com.example.payapp.view.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.payapp.R;
import com.example.payapp.view.Activity.PayActivity;
import com.example.payapp.view.Constant;

/**
 * Created by Zoker on 2017/3/2.
 */
public class MainFragment extends Fragment{
    View root_view,collectionView,qrView;

   public static MainFragment getInstance(){
       MainFragment mainFragment=new MainFragment();
       return mainFragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root_view=inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        initListener();
        return root_view;
    }
    public void initView(){
        qrView=root_view.findViewById(R.id.home_qr);
        collectionView=root_view.findViewById(R.id.home_collection);
    }
    public void initListener(){
        qrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        collectionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),PayActivity.class);
                intent.putExtra(Constant.PAY_TYPE,Constant.Pay_Type.COLLECTION);
                startActivity(intent);
            }
        });
    }
}
