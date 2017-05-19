package com.zekers.myuidemo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zekers.ui.listener.LoadDataScrollController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * 1 探讨如何不利用第三方实现上拉加载
 * Created by Administrator on 2017/1/17.
 */
public class RecyclerViewActivity extends AppCompatActivity implements LoadDataScrollController.OnRecycleRefreshListener{
    RecyclerView recyclerView;
    List<String> mDatas=new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefresh;
    private LoadDataScrollController mController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_1);
        recyclerView=(RecyclerView) findViewById(R.id.Rc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        String str[]=getResources().getStringArray(R.array.titles);
        for(int i=0;i<str.length;i++)
        {
            mDatas.add(str[i]);
        }


        mSwipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipeFresh);
        mSwipeRefresh.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        /**
         * 创建控制器，同时使当前activity实现数据监听回调接口
         */
        mController =new LoadDataScrollController(this);
        /**
         * 设置监听
         */
        recyclerView.addOnScrollListener(mController);
        mSwipeRefresh.setOnRefreshListener(mController);
    }

    @Override
    public void refresh() {
        //刷新的接口调用
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                mSwipeRefresh.setRefreshing(false);
                mController.setLoadDataStatus(false);
            }
        },500);
    }
    ProgressDialog pd;
    @Override
    public void loadMore() {
        //加载更多的接口回调
        pd=new ProgressDialog(this);
        pd.show();
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                //设置数据加载结束的监听状态
                mController.setLoadDataStatus(false);
                pd.dismiss();
            }
        },1000);
    }



    private RecyclerView.Adapter adapter=new RecyclerView.Adapter() {
        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDatas==null?0:mDatas.size();
        }

        class VH extends RecyclerView.ViewHolder{
            public VH(View itemView) {
                super(itemView);
            }
        }
    };

}
