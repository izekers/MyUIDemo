package com.zekers.control;

import com.zekers.ui.view.recycler.VisitableTypeControl;

/**
 *
 * Created by Administrator on 2017/1/17.
 */
public class One implements VisitableTypeControl.Visitable {

    @Override
    public int type(VisitableTypeControl.TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
