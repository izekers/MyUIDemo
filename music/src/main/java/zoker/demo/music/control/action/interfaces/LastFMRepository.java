package zoker.demo.music.control.action.interfaces;

import java.io.File;

import rx.Observable;
import zoker.demo.music.model.net.ArtistInfo;

/**
 * Created by Zoker on 2017/2/24.
 */

public interface LastFMRepository {

    Observable<ArtistInfo> getArtistInfo(String artist);
}
