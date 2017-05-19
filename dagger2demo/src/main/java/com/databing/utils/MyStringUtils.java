package com.databing.utils;

/**
 * Created by Administrator on 2017/2/20.
 */

public class MyStringUtils {
    public static String capitalize(final String word){
        if (word!=null && word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
