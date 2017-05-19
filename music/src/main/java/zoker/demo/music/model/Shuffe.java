package zoker.demo.music.model;

import com.zekers.ui.view.recycler.VisitableTypeControl;

import zoker.demo.music.R;

/**
 *
 * Created by Zoker on 2017/2/22.
 */
public class Shuffe implements VisitableTypeControl.Visitable{
    private String title;
    @Override
    public int type(VisitableTypeControl.TypeFactory typeFactory) {
        return R.layout.item_play_shuffle;
    }
}
