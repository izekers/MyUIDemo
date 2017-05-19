package com.zekers.ui.view.recycler.typeControl;

import android.view.View;

/**
 * 分类工厂，所有的model都需要在这里登记
 * Created by Zekers on 2017/1/18.
 */
public interface VisitableTypeFactory {
    //例子方法
    int type(Demo_Model demo_model);
    VisitableViewHolder createViewHolder(int type, View itemView);
}
