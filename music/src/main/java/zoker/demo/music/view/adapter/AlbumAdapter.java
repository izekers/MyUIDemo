package zoker.demo.music.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.ui.view.recycler.VisitableViewHolder;

import java.util.ArrayList;
import java.util.List;

import zoker.demo.music.model.Shuffe;
import zoker.demo.music.model.Song;

/**
 * 这里只用在意排列的问题，不需要在意怎么分类，数据的绑定情况
 * Created by Zoker on 2017/2/22.
 */
public class AlbumAdapter extends RecyclerView.Adapter<VisitableViewHolder> {
    private VisitableTypeControl.TypeFactory typeFactory;
    private List<VisitableTypeControl.Visitable> mDatas;
    private Shuffe shuffe;
    public AlbumAdapter(VisitableTypeControl.TypeFactory typeFactory) {
        this.typeFactory = typeFactory;
        this.mDatas=new ArrayList<>();
        shuffe=new Shuffe();
    }

    @Override
    public VisitableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(viewType, parent);
    }

    @Override
    public void onBindViewHolder(VisitableViewHolder holder, int position) {
        holder.setUpView(mDatas.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        if (mDatas == null || mDatas.isEmpty())
            return 0;
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas != null) {
            return mDatas.get(position).type(typeFactory);
        }
        return super.getItemViewType(position);
    }

    public List<VisitableTypeControl.Visitable> getmDatas() {
        return mDatas;
    }

    public void setSongs(List<Song> songs) {
        mDatas.clear();
        mDatas.add(shuffe);
        mDatas.addAll(songs);
    }
}
