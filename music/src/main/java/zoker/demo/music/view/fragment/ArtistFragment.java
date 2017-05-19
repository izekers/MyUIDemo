package zoker.demo.music.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import zoker.demo.music.Constants;

/**
 * Created by Zoker on 2017/2/21.
 */

public class ArtistFragment extends Fragment{
    public static Fragment newInstance(Constants.PLAYLIST_TYPE  action){
        Bundle args=new Bundle();
        ArtistFragment fragment=new ArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
