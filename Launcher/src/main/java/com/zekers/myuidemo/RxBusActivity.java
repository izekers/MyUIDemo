package com.zekers.myuidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zekers.event.FirstEvent;
import com.zekers.event.SecondEvent;
import com.zekers.utils.rx.EventBus.Events;
import com.zekers.utils.rx.EventBus.RxBus;
import com.zekers.view.RxFragment;

/**
 * Created by Zoker on 2017/2/24.
 */

public class RxBusActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        getSupportFragmentManager().beginTransaction().replace(R.id.rx_frame,new RxFragment()).commit();
    }
    public void sendFirst(View view){
        RxBus.getInstance().send(Events.First,new FirstEvent("first"));
    }
    public void sendSecond(View view){
        RxBus.getInstance().send(Events.Second,"second");
    }
    public void jump(View view){
        startActivity(new Intent(RxBusActivity.this,RxBus2Activity.class));
    }
}
