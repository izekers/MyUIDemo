package com.zekers.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zekers.face.MusicInterface;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MusicService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicController();
    }
    class MusicController extends Binder implements MusicInterface {
        @Override
        public void paly() {
            MusicService.this.play();
        }

        @Override
        public void pause(){
            MusicService.this.pause();
        }
        public void daMaJiang(){
            Toast.makeText(MusicService.this,"陪领导打麻将",Toast.LENGTH_SHORT).show();
        }
    }
    public void play(){
        Toast.makeText(MusicService.this,"播放音乐",Toast.LENGTH_SHORT).show();
    }
    public void pause(){
        Toast.makeText(MusicService.this,"停止播放音乐",Toast.LENGTH_SHORT).show();
    }
}
