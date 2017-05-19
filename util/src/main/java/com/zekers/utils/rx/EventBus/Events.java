package com.zekers.utils.rx.EventBus;

import android.support.annotation.IntDef;

import com.google.gson.Gson;
import com.zekers.utils.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Event管家
 * Created by zekers on 2016/9/22.
 */
public class Events<T> {

    //所有事件的CODE
    public static final int First = 1; //点击事件
    public static final int Second = 21; //其它事件
    public static final int Results = 22; //其它事件

    public static final int Meta=2;
    public static final int Favour=3;
    public static final int Recently = 4;
    public static final int MediaUpdate=5;
    public static final int Link=6;
    public static final int UNLink=7;

    //枚举
    @IntDef({First, Second ,Results ,Meta ,Favour ,Recently ,MediaUpdate,Link,UNLink})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventCode {}


    public @Events.EventCode int code;
    public T content;

    public static <O> Events<O> setContent(O t) {
        Events<O> events = new Events<>();
        events.content = t;
        return events;
    }

    public <T> T getContent() {
        Logger.d(new Gson().toJson(content));
        return (T) content;
    }

}