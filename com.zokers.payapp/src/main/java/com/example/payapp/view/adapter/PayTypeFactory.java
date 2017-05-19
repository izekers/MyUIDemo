package com.example.payapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.payapp.R;
import com.example.payapp.model.Details;
import com.example.payapp.model.Records;
import com.example.payapp.view.adapter.viewholder.PayViewHolder;
import com.example.payapp.view.adapter.viewholder.RecordsViewHolder;
import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.ui.view.recycler.VisitableViewHolder;
import com.zekers.utils.logger.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里处理分类的情况，其他的任何情况都不处理
 * Created by Zoker on 2017/2/22.
 */
public class PayTypeFactory implements VisitableTypeControl.TypeFactory {
    private final int records_type = 1;
    private final int collection_type = 2;
    private final int pay_type = 3;
    private static PayTypeFactory instance;
    private Map<Integer, Integer> res_map;

    {
        res_map = new HashMap<>();
        res_map.put(records_type, R.layout.item_records);
        res_map.put(collection_type, R.layout.item_pay);
        res_map.put(pay_type, R.layout.item_pay);
    }

    private PayTypeFactory() {
    }

    public static PayTypeFactory getInstance() {
        if (instance == null) {
            synchronized (PayTypeFactory.class) {
                instance = new PayTypeFactory();
            }
        }
        return instance;
    }

    @Override
    public int type(VisitableTypeControl.Visitable visitable) {
        if (visitable instanceof Records)
            return type((Records) visitable);
        if (visitable instanceof Details)
            return type((Details) visitable);
        return 0;
    }

    public int type(Records records) {
        return records_type;
    }

    public int type(Details details) {
        return pay_type;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case records_type:
                return new RecordsViewHolder(itemView);
            case pay_type:
                return new PayViewHolder(itemView);
        }
        return null;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, ViewGroup parent) {
        switch (type) {
            case records_type:
                return null;
        }
        return null;
    }

    @Override
    public int getRes(int type) {
        return res_map.get(type);
    }
}
