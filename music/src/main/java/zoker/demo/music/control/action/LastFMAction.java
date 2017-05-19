package zoker.demo.music.control.action;

import java.io.File;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import zoker.demo.music.control.action.api.LastFmApiService;
import zoker.demo.music.control.action.interfaces.LastFMRepository;
import zoker.demo.music.model.net.ArtistInfo;

/**
 * Created by Zoker on 2017/2/24.
 */
public class LastFMAction implements LastFMRepository {
    private LastFmApiService mLastFmApiService;

    public LastFMAction(Retrofit retrofit) {
        mLastFmApiService=retrofit.create(LastFmApiService.class);
    }

    @Override
    public Observable<ArtistInfo> getArtistInfo(String artist) {
        return mLastFmApiService.getArtistInfo(artist);
    }
}
