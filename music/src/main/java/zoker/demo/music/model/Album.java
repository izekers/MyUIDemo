package zoker.demo.music.model;

import com.zekers.ui.view.recycler.VisitableTypeControl;

/**
 * Created by hefuyi on 2016/11/3.
 */

public class Album implements VisitableTypeControl.Visitable{
    public final long artistId;
    public final String artistName;
    public final long id;
    public final int songCount;
    public final String title;
    public final int year;

    public Album() {
        this.id = -1;
        this.title = "";
        this.artistName = "";
        this.artistId = -1;
        this.songCount = -1;
        this.year = -1;
    }

    public Album(long _id, String _title, String _artistName, long _artistId, int _songCount, int _year) {
        this.id = _id;
        this.title = _title;
        this.artistName = _artistName;
        this.artistId = _artistId;
        this.songCount = _songCount;
        this.year = _year;
    }

    @Override
    public int type(VisitableTypeControl.TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
