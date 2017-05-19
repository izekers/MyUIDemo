package com.zekers.control;

import android.view.View;
import android.view.ViewGroup;

import com.zekers.myuidemo.R;
import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.ui.view.recycler.VisitableViewHolder;
import com.zekers.viewHolder.OneViewHolder;
import com.zekers.viewHolder.TwoViewHolder;

/**
 *
 * Created by Administrator on 2017/1/17.
 */
public class TypeFactoryForList implements VisitableTypeControl.TypeFactory {
    private final int TYPE_RESOURCE_ONE = R.layout.item;
    private final int TYPE_RESOURCE_TWO = R.layout.item2;

    @Override
    public int type(VisitableTypeControl.Visitable visitable) {
        if (visitable instanceof One)
            return type((One) visitable);
        if (visitable instanceof Two)
            return type((Two) visitable);
        return -1;
    }

    private int type(One One) {
        return TYPE_RESOURCE_ONE;
    }

    private int type(Two two) {
        return TYPE_RESOURCE_TWO;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case TYPE_RESOURCE_TWO:
                return new TwoViewHolder(itemView);
            case TYPE_RESOURCE_ONE:
                return new OneViewHolder(itemView);
        }
        return null;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, ViewGroup parent) {
        return null;
    }

    @Override
    public int getRes(int type) {
        return 0;
    }
}
