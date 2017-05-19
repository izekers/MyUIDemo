package com.zekers.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zekers.event.FirstEvent;
import com.zekers.myuidemo.R;
import com.zekers.utils.logger.Logger;
import com.zekers.utils.rx.EventBus.Events;
import com.zekers.utils.rx.EventBus.RxBus;

import rx.functions.Action1;

/**
 * Created by Zoker on 2017/2/24.
 */
public class RxFragment extends Fragment {
    TextView tagView;
    View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rx_bus, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tagView = (TextView) view.findViewById(R.id.rx_tag);
        //每次都要重新订阅
        RxBus.getSubscriber().setEvent(Events.First)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        FirstEvent content = events.getContent();
                        tagView.setText(content.getName());
                    }
                })
                .create();
        RxBus.getSubscriber().setEvent(Events.Second)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        String content = events.getContent();
                        tagView.setText(content);
                    }
                })
                .create();

        RxBus.getSubscriber().setEvent(Events.Results)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        String content = events.getContent();
                        tagView.setText(content);
                    }
                })
                .create();
    }
}
