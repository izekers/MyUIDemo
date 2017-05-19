package zoker.demo.music.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.ui.view.recycler.VisitableViewHolder;

import java.util.HashMap;
import java.util.Map;

import zoker.demo.music.R;
import zoker.demo.music.databinding.ItemListLinearLayoutItemBinding;
import zoker.demo.music.databinding.ItemPlayShuffleBinding;
import zoker.demo.music.model.Album;
import zoker.demo.music.model.Shuffe;
import zoker.demo.music.model.Song;
import zoker.demo.music.view.adapter.viewholder.AlbumViewHolder;
import zoker.demo.music.view.adapter.viewholder.PlayShuffleViewHolder;
import zoker.demo.music.view.adapter.viewholder.SongListViewHolder;

/**
 * 这里处理分类的情况，其他的任何情况都不处理
 * Created by Zoker on 2017/2/22.
 */
public class MusicListTypeFactory implements VisitableTypeControl.TypeFactory{
    private final int title_res= 1;
    private final int song_res=2;
    private final int album_res=3;
    private static MusicListTypeFactory instance;
    private Map<Integer,Integer> res_map;
    {
        res_map=new HashMap<>();
        res_map.put(title_res,R.layout.item_play_shuffle);
        res_map.put(song_res,R.layout.item_list_linear_layout_item);
        res_map.put(album_res,R.layout.item_list_linear_layout_item);
    }

    private MusicListTypeFactory(){}
    public static MusicListTypeFactory getInstance(){
        if (instance==null){
            synchronized (MusicListTypeFactory.class){
                instance=new MusicListTypeFactory();
            }
        }
        return instance;
    }

    @Override
    public int type(VisitableTypeControl.Visitable visitable) {
        return type((Shuffe)visitable);
    }

    public int type(Shuffe title) {
        return title_res;
    }

    public int type(Song song){
        return song_res;
    }

    public int type(Album song){
        return album_res;
    }
    @Override
    public VisitableViewHolder createViewHolder(int type, View itemView) {
        switch (type){
            case title_res:
                return new PlayShuffleViewHolder(itemView);
            case song_res:
                return new SongListViewHolder(itemView);
            case album_res:
                return new AlbumViewHolder(itemView);
        }
        return null;
    }

    @Override
    public VisitableViewHolder createViewHolder(int type, ViewGroup parent) {
        switch (type){
            case title_res:
                ItemPlayShuffleBinding shuffleBinding=ItemPlayShuffleBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new PlayShuffleViewHolder(shuffleBinding.rootView).setBinding(shuffleBinding);
            case song_res:
                ItemListLinearLayoutItemBinding itemBinding=ItemListLinearLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new SongListViewHolder(itemBinding.rootView).setBinding(itemBinding);
            case album_res:
                ItemListLinearLayoutItemBinding itemListLinearLayoutItemBinding=ItemListLinearLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new AlbumViewHolder(itemListLinearLayoutItemBinding.rootView);
                default:
        }
        return null;
    }

    @Override
    public int getRes(int type) {
        return res_map.get(type);
    }
}
