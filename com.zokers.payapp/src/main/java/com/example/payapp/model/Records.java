package com.example.payapp.model;

import com.zekers.ui.view.recycler.VisitableTypeControl;

/**
 * Created by Zoker on 2017/3/2.
 */

public class Records implements VisitableTypeControl.Visitable{
    @Override
    public int type(VisitableTypeControl.TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
