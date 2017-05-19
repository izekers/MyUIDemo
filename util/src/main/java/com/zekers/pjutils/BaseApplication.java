package com.zekers.pjutils;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2017/1/22.
 */

public class BaseApplication extends Application {
    public static BaseApplication Instance;
    public void onCreate() {
        Instance = this;
        super.onCreate();
        FlowManager.init(this);
    }
}
