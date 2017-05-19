package com.zekers.ui.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 *
 * Created by Administrator on 2017/1/17.
 */
public abstract class VisitableViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View mItemView;

    public VisitableViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.mItemView = itemView;
    }

    public View getView(int resID) {
        View view = views.get(resID);
        if (view == null) {
            view = mItemView.findViewById(resID);
            views.put(resID, view);
        }
        return view;
    }

    public abstract void setUpView(T model, int position, RecyclerView.Adapter adapter);
}
