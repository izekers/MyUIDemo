package zoker.demo.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import zoker.demo.music.MusicPlayer;

/**
 * Created by Zoker on 2017/2/27.
 */

public class MusicService extends Service{
    private MusicPlayer.ServiceToken mToken;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
