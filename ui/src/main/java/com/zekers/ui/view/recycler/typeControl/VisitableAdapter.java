package com.zekers.ui.view.recycler.typeControl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 使用了Visitale解耦模式的基础适配器，可以在此基础上做参考修改
 * Created by Zekers on 2017/1/18.
 */
public class VisitableAdapter extends RecyclerView.Adapter<VisitableViewHolder> {
    private VisitableTypeFactory typeFactory;
    private List<Visitable> mDatas;
    public VisitableAdapter(VisitableTypeFactory typeFactory, List<Visitable> mDatas){
        this.typeFactory=typeFactory;
        this.mDatas=mDatas;
    }
    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).type(typeFactory);
    }

    @Override
    public VisitableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0)
            return null;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(VisitableViewHolder holder, int position) {
        holder.setUpView(mDatas.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}
