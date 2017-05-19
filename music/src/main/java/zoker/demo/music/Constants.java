package zoker.demo.music;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量表
 * Created by Zoker on 2017/2/21.
 */
public class Constants {
    public static final String LIST_TYPE="list_type";
    public enum PLAYLIST_TYPE {
        RECENTPLAY, RECENTADD, TOPPLAYED, ALLSONG, FAVOURATE;
        public static Map<PLAYLIST_TYPE, Integer> titles;

        static {
            titles = new HashMap<>();
            titles.put(ALLSONG, R.string.library);
            titles.put(RECENTADD, R.string.recent_add);
            titles.put(RECENTPLAY, R.string.recent_play);
            titles.put(FAVOURATE, R.string.favourate);
        }

        public int getTitle() {
            return titles.get(this);
        }
    }

    //HTTP相关设置
    public static final int HTTP_CACHE_SIZE = 20 * 1024 * 1024;
    public static final int HTTP_CONNECT_TIMEOUT = 15 * 1000;
    public static final int HTTP_READ_TIMEOUT = 20 * 1000;

    //端口
    public static final String BASE_API_URL_LASTFM = "http://ws.audioscrobbler.com/2.0/";
    public static final String BASE_API_URL_KUGOU = "http://lyrics.kugou.com/";
}
