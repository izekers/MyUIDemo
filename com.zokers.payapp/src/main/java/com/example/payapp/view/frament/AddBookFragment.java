package com.example.payapp.view.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.payapp.R;
import com.zekers.ui.view.recycler.RecyclerWrapView;
import com.zekers.ui.view.widget.AbilityToolBar;

/**
 * Created by Zoker on 2017/3/2.
 */
public class AddBookFragment extends Fragment{
    View view;
    RecyclerView recyclerWrapView;
    AbilityToolBar abilityToolBar;
   public static AddBookFragment getInstance(){
       AddBookFragment addBookFragment=new AddBookFragment();
       return addBookFragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_add_book,container,false);
        recyclerWrapView=(RecyclerWrapView)view.findViewById(R.id.rw_list);
        abilityToolBar=(AbilityToolBar)view.findViewById(R.id.ability_toolbar);
        abilityToolBar.setTitle(getContext().getResources().getString(R.string.the_address_book));
        return view;
    }
}
