package zoker.demo.music.control.action.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import zoker.demo.music.model.net.KuGouRawLyric;
import zoker.demo.music.model.net.KuGouSearchLyricResult;

/**
 * Created by hefuyi on 2017/1/20.
 */

public interface KuGouApiService {

    @GET("search?ver=1&man=yes&client=pc")
    Observable<KuGouSearchLyricResult> searchLyric(@Query("keyword") String songName, @Query("duration") String duration);

    @GET("download?ver=1&client=pc&fmt=lrc&charset=utf8")
    Observable<KuGouRawLyric> getRawLyric(@Query("id") String id, @Query("accesskey") String accesskey);
}
