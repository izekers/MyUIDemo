package com.example.payapp.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.payapp.R;
import com.example.payapp.model.Details;
import com.example.payapp.model.Records;
import com.zekers.ui.view.recycler.VisitableViewHolder;

/**
 * Created by Zoker on 2017/3/2.
 */

public class PayViewHolder extends VisitableViewHolder<Details>{
    public PayViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Details model, int position, RecyclerView.Adapter adapter) {
    }
}
