package zoker.demo.music.control.presenter;

import com.zekers.utils.rx.RxHelper;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import zoker.demo.music.Constants;
import zoker.demo.music.control.contract.SongsContract;
import zoker.demo.music.control.usecase.GetSongs;
import zoker.demo.music.model.Song;

/**
 * P层
 * Created by Zoker on 2017/2/23.
 */

public class SongsPresenter implements SongsContract.Presenter{
    private SongsContract.View mView;
    private CompositeSubscription mCompositeSubscription;

    private GetSongs mUsecase;
    public SongsPresenter(GetSongs getSongs) {
        this.mUsecase=getSongs;
    }

    @Override
    public void loadSongs(Constants.PLAYLIST_TYPE action) {
        mCompositeSubscription.clear();
        Subscription subscription = mUsecase.execute(new GetSongs.RequestValues(action))
                .getSongList()
                .compose(RxHelper.<List<Song>>applySchedulers())
                .subscribe(new Action1<List<Song>>() {
                    @Override
                    public void call(List<Song> songList) {
                        if (songList == null || songList.size() == 0) {
                            mView.showEmptyView();
                        } else {
                            mView.showSongs(songList);
                        }
                    }
                });
        mCompositeSubscription.add(subscription);
    }
    @Override
    public void attachView(SongsContract.View view) {
        this.mView=view;
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

}
