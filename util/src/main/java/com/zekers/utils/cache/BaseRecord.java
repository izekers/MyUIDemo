package com.zekers.utils.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.zekers.pjutils.BaseApplication;

/**
 * SP记录类
 * Created by zekers on 2016/10/20.
 */
public abstract class BaseRecord<T> {
    private static String TAG=BaseRecord.class.getSimpleName();
    private static SharedPreferences mSharedPreferences;

    public BaseRecord(){
        mSharedPreferences= BaseApplication.Instance.getSharedPreferences(SPName(), Context.MODE_PRIVATE);
    }

    public <T> boolean save(T t){
        if (mSharedPreferences == null) {
            return false;
        }
        clearInfo();
        return Save(getEditor(),t);
    }
    public <T> T load(){
        if (mSharedPreferences == null) {
            return null;
        }
        return load(mSharedPreferences);
    }
    public SharedPreferences.Editor getEditor(){
        if (mSharedPreferences == null) {
            return null;
        }
        return mSharedPreferences.edit();
    }
    /**
     * 清除课程相关数据
     * @return
     */
    public  void clearInfo(){
        if (mSharedPreferences == null) {
            return ;
        }
        mSharedPreferences.edit().clear().commit();
    }
    abstract <T> boolean Save(SharedPreferences.Editor editor,T t);
    abstract <T> T load(SharedPreferences sharedPreferences);
    abstract String SPName();
    abstract String TAG();
}
