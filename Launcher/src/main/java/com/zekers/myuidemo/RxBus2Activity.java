package com.zekers.myuidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zekers.utils.rx.EventBus.Events;
import com.zekers.utils.rx.EventBus.RxBus;

import org.w3c.dom.Text;

import rx.functions.Action1;

/**
 * Created by Zoker on 2017/2/24.
 */

public class RxBus2Activity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus2);
        textView=(TextView)findViewById(R.id.result);
        RxBus.getSubscriber().setEvent(Events.Results)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        String string=events.getContent();
                        textView.setText(string);
                    }
                })
                .create();
    }
    public void sendResult(View view){
        RxBus.getInstance().send(Events.Results,"results");
    }
}
