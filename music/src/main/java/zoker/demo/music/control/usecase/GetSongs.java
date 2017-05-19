package zoker.demo.music.control.usecase;

import java.util.List;

import rx.Observable;
import zoker.demo.music.Constants;
import zoker.demo.music.control.action.interfaces.DbRepository;
import zoker.demo.music.control.action.interfaces.KuGouRepository;
import zoker.demo.music.model.Song;

/**
 * Created by hefuyi on 2016/11/12.
 */

public class GetSongs extends UseCase<GetSongs.RequestValues,GetSongs.ResponseValue>{

    private final DbRepository dbRepository;
    private final KuGouRepository kuGouRepository;

    public GetSongs(DbRepository dbRepository, KuGouRepository kuGouRepository) {
        this.dbRepository = dbRepository;
        this.kuGouRepository = kuGouRepository;
    }


    @Override
    public ResponseValue execute(RequestValues requestValues) {
        Constants.PLAYLIST_TYPE action = requestValues.getAction();
        switch (action) {
            case RECENTADD:
                return new ResponseValue(dbRepository.getRecentlyAddedSongs());
            case RECENTPLAY:
                return new ResponseValue(dbRepository.getRecentlyPlayedSongs());
            case ALLSONG:
                return new ResponseValue(dbRepository.getAllSongs());
            case TOPPLAYED:
                return new ResponseValue(dbRepository.getTopPlaySongs());
//            case Constants.NAVIGATE_QUEUE:
//                return new ResponseValue(mRepository.getQueueSongs());
            case FAVOURATE:
                return new ResponseValue(dbRepository.getFavourateSongs());
            default:
                throw new RuntimeException("wrong action type");
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final Constants.PLAYLIST_TYPE action;

        public RequestValues(Constants.PLAYLIST_TYPE action) {
            this.action = action;
        }

        public Constants.PLAYLIST_TYPE getAction() {
            return action;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Observable<List<Song>> mListObservable;

        public ResponseValue(Observable<List<Song>> listObservable) {
            mListObservable = listObservable;
        }

        public Observable<List<Song>> getSongList(){
            return mListObservable;
        }
    }
}
