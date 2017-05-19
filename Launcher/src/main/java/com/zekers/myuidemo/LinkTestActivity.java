package com.zekers.myuidemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.zekers.Service.LinkApi;
import com.zekers.network.base.HttpMethod;
import com.zekers.network.base.OkHttpClientManager;
import com.zekers.utils.logger.Logger;
import com.zekers.utils.rx.EventBus.Events;
import com.zekers.utils.rx.EventBus.RxBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by Zoker on 2017/3/29.
 */

public class LinkTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linktest);
        new Thread() {
            @Override
            public void run() {
                super.run();
                link1();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                link2();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                link3();
            }
        }.start();
    }

    public void isLink(TextView view) {
        view.setText("线路接通");
        view.setTextColor(Color.GREEN);
    }

    public void unLink(TextView view) {
        view.setText("线路出错");
        view.setTextColor(Color.RED);
    }

    TextView statu_1_1, statu_1_2, statu_1_3, statu_1_4;

    public void link1() {
        statu_1_1 = (TextView) findViewById(R.id.statu_1_1);
        statu_1_2 = (TextView) findViewById(R.id.statu_1_2);
        statu_1_3 = (TextView) findViewById(R.id.statu_1_3);
        statu_1_4 = (TextView) findViewById(R.id.statu_1_4);
        OkHttpClient okHttpClient = OkHttpClientManager.getClient();
        Request request1 = new Request.Builder()
                .url("http://10.201.78.24:8088/")
                .build();
        Call call1 = okHttpClient.newCall(request1);
        Request request2 = new Request.Builder()
                .url("http://10.201.78.24:8090/ms3/")
                .build();
        Call call2 = okHttpClient.newCall(request2);
        Request request3 = new Request.Builder()
                .url("http://10.201.78.24:8091/msweb4/")
                .build();
        Call call3 = okHttpClient.newCall(request3);
        Request request4 = new Request.Builder()
                .url("https://10.201.78.253:20143/")
                .build();
        Call call4 = okHttpClient.newCall(request4);

        try {
            call1.execute();
            statu_1_1.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_1_1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_1_1.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_1_1);
                }
            });
        }

        try {
            call2.execute();
            statu_1_2.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_1_2);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_1_2.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_1_2);
                }
            });
        }

        try {
            call3.execute();
            statu_1_3.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_1_3);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_1_3.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_1_3);
                }
            });
        }


        try {
            call4.execute();
            statu_1_4.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_1_4);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_1_4.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_1_4);
                }
            });
        }
    }

    TextView statu_2_1, statu_2_2, statu_2_3, statu_2_4;

    public void link2() {
        statu_2_1 = (TextView) findViewById(R.id.statu_2_1);
        statu_2_2 = (TextView) findViewById(R.id.statu_2_2);
        statu_2_3 = (TextView) findViewById(R.id.statu_2_3);
        statu_2_4 = (TextView) findViewById(R.id.statu_2_4);
        OkHttpClient okHttpClient = OkHttpClientManager.getClient();
        Request request1 = new Request.Builder()
                .url("https://edu.bingocc.com:20085")
                .build();
        Call call1 = okHttpClient.newCall(request1);

        Request request2 = new Request.Builder()
                .url("https://edu.bingocc.com:20137/ms/3")
                .build();
        Call call2 = okHttpClient.newCall(request2);

        Request request3 = new Request.Builder()
                .url("http://edu.bingocc.com:8083/msweb4")
                .build();
        Call call3 = okHttpClient.newCall(request3);

        Request request4 = new Request.Builder()
                .url("https://edu.bingocc.com:20146")
                .build();
        Call call4 = okHttpClient.newCall(request4);


        try {
            call1.execute();
            statu_2_1.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_2_1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_2_1.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_2_1);
                }
            });
        }

        try {
            call2.execute();
            statu_2_2.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_2_2);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_2_2.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_2_2);
                }
            });
        }

        try {
            call3.execute();
            statu_2_3.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_2_3);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_2_3.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_2_3);
                }
            });
        }


        try {
            call4.execute();
            statu_2_4.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_2_4);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_2_4.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_2_4);
                }
            });
        }
    }

    TextView statu_3_1, statu_3_2, statu_3_3, statu_3_4, statu_3_5;

    public void link3() {
        statu_3_1 = (TextView) findViewById(R.id.statu_3_1);
        statu_3_2 = (TextView) findViewById(R.id.statu_3_2);
        statu_3_3 = (TextView) findViewById(R.id.statu_3_3);
        statu_3_4 = (TextView) findViewById(R.id.statu_3_4);
        statu_3_5 = (TextView) findViewById(R.id.statu_3_5);
        OkHttpClient okHttpClient = OkHttpClientManager.getClient();
        Request request1 = new Request.Builder()
                .url("https://mms.depts.bingosoft.net:20153")
                .build();
        Call call1 = okHttpClient.newCall(request1);
        Request request2 = new Request.Builder()
                .url("https://mms.depts.bingosoft.net:20157/ms/")
                .build();
        Call call2 = okHttpClient.newCall(request2);
        Request request3 = new Request.Builder()
                .url("http://mms.depts.bingosoft.net:8082/msweb")
                .build();
        Call call3 = okHttpClient.newCall(request3);

        Request request4 = new Request.Builder()
                .url("https://mms.depts.bingosoft.net:20154")
                .build();
        Call call4 = okHttpClient.newCall(request4);

        Request request5 = new Request.Builder()
                .url("http://mms.depts.bingosoft.net:8083")
                .build();
        Call call5 = okHttpClient.newCall(request5);


        try {
            call1.execute();
            statu_3_1.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_3_1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_3_1.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_3_1);
                }
            });
        }

        try {
            call2.execute();
            statu_3_2.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_3_2);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_3_2.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_3_2);
                }
            });
        }

        try {
            call3.execute();
            statu_3_3.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_3_3);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_3_3.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_3_3);
                }
            });
        }


        try {
            call4.execute();
            statu_3_4.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_3_4);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_3_4.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_3_4);
                }
            });
        }
        try {
            call5.execute();
            statu_3_5.post(new Runnable() {
                @Override
                public void run() {
                    isLink(statu_3_5);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            statu_3_5.post(new Runnable() {
                @Override
                public void run() {
                    unLink(statu_3_5);
                }
            });
        }
    }
}
