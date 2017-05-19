package zoker.demo.music.view.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.zekers.pjutils.BaseApplication;
import com.zekers.ui.view.recycler.VisitableViewHolder;

import zoker.demo.music.R;
import zoker.demo.music.databinding.ItemListLinearLayoutItemBinding;
import zoker.demo.music.model.Song;

/**
 * Created by Zoker on 2017/2/22.
 */

public class SongListViewHolder extends VisitableViewHolder<Song> {
    ItemListLinearLayoutItemBinding binding;

    public SongListViewHolder(View itemView) {
        super(itemView);
    }

    public SongListViewHolder setBinding(ItemListLinearLayoutItemBinding binding) {
        this.binding = binding;
        return this;
    }

    @Override
    public void setUpView(Song model, int position, RecyclerView.Adapter adapter) {
        binding.textItemTitle.setText(model.title);
        binding.textItemSubtitle.setText(model.artistName);
        binding.textItemSubtitle2.setText(model.albumName);

//        Glide.with(holder.itemView.getContext()).load(ListenerUtil.getAlbumArtUri(localItem.albumId).toString())
//                .error(ATEUtil.getDefaultAlbumDrawable(mContext))
//                .placeholder(ATEUtil.getDefaultAlbumDrawable(mContext))
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .centerCrop()
//                .into(itemHolder.albumArt);

        binding.textItemTitle.setTextColor(BaseApplication.Instance.getResources().getColor(com.zekers.ui.R.color.gray_f2));

//        if (topPlayScore != -1) {
//            itemHolder.playscore.setVisibility(View.VISIBLE);
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) itemHolder.playscore.getLayoutParams();
//            int full = DensityUtil.getScreenWidth(mContext);
//            layoutParams.width = (int) (full * (localItem.getPlayCountScore() / topPlayScore));
//        }

        setOnPopupMenuListener(model,position);
    }

    public void setOnPopupMenuListener(final Song model,int position) {
        binding.popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu menu = new PopupMenu(itemView.getContext(), v);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popup_song_play_next:
                                long[] ids = new long[1];
                                ids[0] = model.id;
//                                MusicPlayer.playNext(mContext, ids, -1, ListenerUtil.IdType.NA);
                                break;
                            case R.id.popup_song_goto_album:
//                                NavigationUtil.goToAlbum(mContext, arraylist.get(realSongPosition).albumId,
//                                        arraylist.get(realSongPosition).title);
                                break;
                            case R.id.popup_song_goto_artist:
//                                NavigationUtil.goToArtist(mContext, arraylist.get(realSongPosition).artistId,
//                                        arraylist.get(realSongPosition).artistName);
                                break;
                            case R.id.popup_song_addto_queue:
                                long[] id = new long[1];
                                id[0] = model.id;
//                                MusicPlayer.addToQueue(mContext, id, -1, ListenerUtil.IdType.NA);
                                break;
                            case R.id.popup_song_addto_playlist:
//                                ListenerUtil.showAddPlaylistDialog(mContext, new long[]{arraylist.get(realSongPosition).id});
                                break;
                            case R.id.popup_song_delete:
                                long[] deleteIds = {model.id};
//                                switch (action) {
//                                    case Constants.NAVIGATE_PLAYLIST_FAVOURATE:
//                                        ListenerUtil.showDeleteFromFavourate(mContext, deleteIds);
//                                        break;
//                                    case Constants.NAVIGATE_PLAYLIST_RECENTPLAY:
//                                        ListenerUtil.showDeleteFromRecentlyPlay(mContext, deleteIds);
//                                        break;
//                                    default:
//                                        ListenerUtil.showDeleteDialog(mContext, arraylist.get(realSongPosition).title, deleteIds,
//                                                new MaterialDialog.SingleButtonCallback() {
//                                                    @Override
//                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                        arraylist.remove(realSongPosition);
//                                                        songIDs = getSongIds();
//                                                        notifyItemRemoved(position);
//                                                    }
//                                                });
//                                        break;
//                                }
                                break;
                        }
                        return false;
                    }
                });
                menu.inflate(R.menu.popup_song);
                menu.show();
            }
        });
    }
}
