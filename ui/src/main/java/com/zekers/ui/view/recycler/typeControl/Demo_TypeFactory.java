package com.zekers.ui.view.recycler.typeControl;

import android.view.View;

import com.zekers.ui.R;

/**
 * 例子工厂
 * Created by Zekers on 2017/1/18.
 */
public class Demo_TypeFactory implements VisitableTypeFactory{
    private final int VISITABLE_TYPE_DEMO= R.layout.demo;
    @Override
    public int type(Demo_Model demo_model) {
        return R.layout.demo;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, View itemView) {
        if (type==VISITABLE_TYPE_DEMO)
            return new Demo_ViewHolder(itemView);
        return null;
    }
}
