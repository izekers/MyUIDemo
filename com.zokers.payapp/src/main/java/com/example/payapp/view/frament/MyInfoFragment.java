package com.example.payapp.view.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.payapp.R;

/**
 * Created by Zoker on 2017/3/2.
 */
public class MyInfoFragment extends Fragment{
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
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }
}
