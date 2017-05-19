package com.zekers.network.action;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zekers.network.base.HttpMethod;
import com.zekers.network.base.RxNetHelper;
import com.zekers.network.data.DataWrapper;
import com.zekers.network.exception.MethodException;
import com.zekers.utils.cache.CacheManager;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/6.
 */

public class RxtestAction {
    private RxtestApi service;
    private final String TAG = this.getClass().getSimpleName();

    {
        service = HttpMethod.getService(HttpMethod.API_RXTEST, RxtestApi.class);
        if (service == null)
            throw new MethodException(MethodException.API_NULL);
    }

    public Observable<RxtestBean> getItem(Context context, String token, String key) {
        return RxNetHelper.hidCache(context, "/Rxtest",true
                , service.getItem(token, key, "android")
                        .compose(RxNetHelper.<RxtestBean>handleResult())
                        .compose(RxNetHelper.<RxtestBean>applySchedulers())
                , new RxNetHelper.CacheLoad<RxtestBean>() {
                    @Override
                    public Type getType() {
                        return new TypeToken<RxtestBean>() {
                        }.getType();
                    }
                });
    }

    public Observable<RxtestBean> getItems(Context context, String token, String key) {
        return RxNetHelper.preCache(context, "Rxtest", false
                , service.getItem(token, key, "android")
                        .compose(RxNetHelper.<RxtestBean>handleResult())
                        .compose(RxNetHelper.<RxtestBean>applySchedulers())
                , new RxNetHelper.CacheLoad<RxtestBean>() {
                    @Override
                    public Type getType() {
                        return new TypeToken<RxtestBean>() {
                        }.getType();
                    }
                });
    }

    public interface RxtestApi {
        @POST("getitem")
        Observable<DataWrapper<RxtestBean>> getItem(@Query("token") String token, @Query("key") String key, @Query("type") String type);
    }
}
