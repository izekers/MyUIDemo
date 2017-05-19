package com.zekers.myuidemo;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zekers.https.HttpsGetThread;
import com.zekers.https.HttpsPostThread;
import com.zekers.network.action.ConfigAction;
import com.zekers.network.action.ConfigHttpAction;
import com.zekers.network.action.RxtestAction;
import com.zekers.network.action.RxtestBean;
import com.zekers.network.base.RxSubscribe;
import com.zekers.network.data.DataWrapper;
import com.zekers.utils.logger.Logger;

import org.apache.http.NameValuePair;


import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {
    private Button btn, btn1, rx_btn, rx_btn2;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();

    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);
        rx_btn = (Button) findViewById(R.id.rx_test);
        rx_btn2 = (Button) findViewById(R.id.rx_test2);
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfigHttpAction().getItem("ZHONGKAI", "ydxytest")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Toast.makeText(MainActivity.this, "返回参数===" + s, Toast.LENGTH_SHORT).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(MainActivity.this, "请求失败===" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                throwable.printStackTrace();
                            }
                        });
//                List<NameValuePair> list = new ArrayList<NameValuePair>();
//                HttpsPostThread thread = new HttpsPostThread(mHandler,
//                        "http://edu.depts.bingosoft.net:8084/home/getitem?token=ZHONGKAI&key=zhongkai&type=ios", list, 200);
//                thread.start();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfigAction().getItem("ZHONGKAI", "ydxytest")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Toast.makeText(MainActivity.this, "返回参数===" + s, Toast.LENGTH_SHORT).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(MainActivity.this, "请求失败===" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                throwable.printStackTrace();
                            }
                        });
//                List<NameValuePair> list = new ArrayList<NameValuePair>();
//                HttpsPostThread thread = new HttpsPostThread(mHandler,
//                        "https://edu.depts.bingosoft.net:20139/home/getitem?token=ZHONGKAI&key=ydxytest&type=ios", list, 200);
//                thread.start();
            }
        });

        rx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RxtestAction().getItem(MainActivity.this,"ZHONGKAI", "zhongkai").subscribe(new RxSubscribe<RxtestBean>() {
                    int i=1;
                    @Override
                    public void onError(String message) {
                        Log.e("MainActivity", "error:" + message);
                        Toast.makeText(MainActivity.this, "error=" + message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(RxtestBean rxtestBean) {
                        Logger.d(i+new Gson().toJson(rxtestBean));
                        i++;
                        Toast.makeText(MainActivity.this, "result=" + new Gson().toJson(rxtestBean), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        i=1;
                    }
                });
            }
        });

        rx_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RxtestAction().getItems(MainActivity.this,"ZHONGKAI", "zhongkai").subscribe(new RxSubscribe<RxtestBean>() {
                    @Override
                    public void onError(String message) {
                        Log.e("MainActivity", "error:" + message);
                        Toast.makeText(MainActivity.this, "error=" + message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(RxtestBean rxtestBean) {
                        Toast.makeText(MainActivity.this, "result=" + new Gson().toJson(rxtestBean), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initData() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = (String) msg.obj;
                switch (msg.what) {
                    case 200:
                        // 请求成功
                        Log.e("TAG", "返回参数===" + result);
                        Toast.makeText(MainActivity.this, "返回参数===" + result, Toast.LENGTH_SHORT).show();
                        break;
                    case 404:
                        // 请求失败
                        Log.e("TAG", "请求失败!");
                        Toast.makeText(MainActivity.this, "请求失败!", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };
    }
}