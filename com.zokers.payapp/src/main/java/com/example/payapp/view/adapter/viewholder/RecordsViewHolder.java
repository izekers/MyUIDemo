package com.example.payapp.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.payapp.R;
import com.example.payapp.model.Records;
import com.zekers.ui.view.recycler.VisitableViewHolder;
import com.zekers.utils.logger.Logger;

/**
 * Created by Zoker on 2017/3/2.
 */

public class RecordsViewHolder extends VisitableViewHolder<Records>{
    ImageView iconView;
    TextView nameView,signView,valueView;
    public RecordsViewHolder(View itemView) {
        super(itemView);
        iconView=(ImageView) itemView.findViewById(R.id.item_icon);
        nameView=(TextView) itemView.findViewById(R.id.item_name);
        signView=(TextView) itemView.findViewById(R.id.item_sign);
        valueView=(TextView) itemView.findViewById(R.id.item_value);
    }

    @Override
    public void setUpView(Records model, int position, RecyclerView.Adapter adapter) {
    }
}
