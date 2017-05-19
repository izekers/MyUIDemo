package com.zekers.ui.view.recycler.typeControl;

/**
 * 例子model
 * Created by zekers on 2017/1/18.
 */
public class Demo_Model implements Visitable {
    @Override
    public int type(VisitableTypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
