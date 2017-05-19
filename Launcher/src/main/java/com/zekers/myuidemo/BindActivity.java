package com.zekers.myuidemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.zekers.Service.MusicService;
import com.zekers.face.MusicInterface;

/**
 *
 * Created by Administrator on 2016/12/27.
 */
public class BindActivity extends Activity{
    MusicInterface musicInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        Intent intent=new Intent(this, MusicService.class);
        //这里需要混合调用
        /**
         * 混合调用：使用startService调用Service,同时使用bindService绑定Service。
         * 反正Service在Activity返回后台时也进入了后台
         */
        startService(intent);
        //第二个参数是一个服务连接对象，活动与服务的关联起很大作用
        bindService(intent,new MusicServiceConn() ,BIND_AUTO_CREATE);
    }

    class MusicServiceConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TAG","onBinder");
            musicInterface=(MusicInterface)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void start(View view){
        musicInterface.paly();
    }
    public void stop(View view){
        musicInterface.pause();
    }
}
