package zoker.demo.music.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Zoker on 2017/2/23.
 */

public class PreferencesUtility {

    private static PreferencesUtility sInstance;
    private static volatile SharedPreferences mPreferences;

    public PreferencesUtility(final Context context) {
        //获取一个默认的文件的SharePreferences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferencesUtility getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (PreferencesUtility.class) {
                if (sInstance == null) {
                    sInstance = new PreferencesUtility(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
