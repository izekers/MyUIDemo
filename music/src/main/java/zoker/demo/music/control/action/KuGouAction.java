package zoker.demo.music.control.action;

import android.content.Context;

import java.io.File;

import javax.inject.Named;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import zoker.demo.music.control.action.api.KuGouApiService;
import zoker.demo.music.control.action.api.LastFmApiService;
import zoker.demo.music.control.action.interfaces.KuGouRepository;
import zoker.demo.music.model.net.ArtistInfo;
import zoker.demo.music.model.net.KuGouRawLyric;
import zoker.demo.music.model.net.KuGouSearchLyricResult;

/**
 * 先放着
 * Created by Zoker on 2017/2/24.
 */
public class KuGouAction implements KuGouRepository{
    private KuGouApiService mKuGouApiService;

    public KuGouAction(Retrofit retrofit){
        this.mKuGouApiService=retrofit.create(KuGouApiService.class);
    }

    @Override
    public Observable<File> downloadLrcFile(final String title, final String artist, final long duration) {
        return mKuGouApiService.searchLyric(title, String.valueOf(duration))
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<KuGouSearchLyricResult, Observable<KuGouRawLyric>>() {
                    @Override
                    public Observable<KuGouRawLyric> call(KuGouSearchLyricResult kuGouSearchLyricResult) {
                        if (kuGouSearchLyricResult.status == 200
                                && kuGouSearchLyricResult.candidates != null
                                && kuGouSearchLyricResult.candidates.size() != 0) {
                            KuGouSearchLyricResult.Candidates candidates = kuGouSearchLyricResult.candidates.get(0);
                            return mKuGouApiService.getRawLyric(candidates.id, candidates.accesskey);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                .map(new Func1<KuGouRawLyric, File>() {
                    @Override
                    public File call(KuGouRawLyric kuGouRawLyric) {
                        if (kuGouRawLyric == null) {
                            return null;
                        }
//                        String rawLyric = LyricUtil.decryptBASE64(kuGouRawLyric.content);
//                        return LyricUtil.writeLrcToLoc(title, artist, rawLyric);
                        return null;
                    }
                });
    }
}
