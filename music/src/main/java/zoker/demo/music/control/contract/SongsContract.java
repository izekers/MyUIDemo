package zoker.demo.music.control.contract;

import java.util.List;

import zoker.demo.music.Constants;
import zoker.demo.music.control.BasePresenter;
import zoker.demo.music.model.Song;
import zoker.demo.music.view.BaseView;

/**
 *
 * Created by Zoker on 2017/2/23.
 */

public interface SongsContract {

    interface View extends BaseView {

        void showSongs(List<Song> songList);

        void showEmptyView();
    }

    interface Presenter extends BasePresenter<View> {

        void loadSongs(Constants.PLAYLIST_TYPE action);
    }
}
