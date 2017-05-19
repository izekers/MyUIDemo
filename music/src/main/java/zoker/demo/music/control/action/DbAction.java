package zoker.demo.music.control.action;

import android.content.Context;

import java.io.File;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import zoker.demo.music.R;
import zoker.demo.music.control.action.dataloader.AlbumLoader;
import zoker.demo.music.control.action.dataloader.AlbumSongLoader;
import zoker.demo.music.control.action.dataloader.ArtistAlbumLoader;
import zoker.demo.music.control.action.dataloader.ArtistLoader;
import zoker.demo.music.control.action.dataloader.ArtistSongLoader;
import zoker.demo.music.control.action.dataloader.FolderLoader;
import zoker.demo.music.control.action.dataloader.LastAddedLoader;
import zoker.demo.music.control.action.dataloader.PlaylistLoader;
import zoker.demo.music.control.action.dataloader.PlaylistSongLoader;
import zoker.demo.music.control.action.dataloader.QueueLoader;
import zoker.demo.music.control.action.dataloader.SongLoader;
import zoker.demo.music.control.action.dataloader.TopTracksLoader;
import zoker.demo.music.control.action.interfaces.DbRepository;
import zoker.demo.music.model.Album;
import zoker.demo.music.model.Artist;
import zoker.demo.music.model.FolderInfo;
import zoker.demo.music.model.Playlist;
import zoker.demo.music.model.Song;
import zoker.demo.music.model.net.ArtistInfo;

/**
 * Created by Zoker on 2017/2/24.
 */

public class DbAction implements DbRepository{
    private Context mContext;

    public DbAction(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Observable<List<Album>> getAllAlbums() {
        return AlbumLoader.getAllAlbums(mContext);
    }

    @Override
    public Observable<Album> getAlbum(long id) {
        return AlbumLoader.getAlbum(mContext, id);
    }

    @Override
    public Observable<List<Album>> getAlbums(String paramString) {
        return AlbumLoader.getAlbums(mContext, paramString);
    }

    @Override
    public Observable<List<Song>> getSongsForAlbum(long albumID) {
        return AlbumSongLoader.getSongsForAlbum(mContext, albumID);
    }

    @Override
    public Observable<List<Album>> getAlbumsForArtist(long artistID) {
        return ArtistAlbumLoader.getAlbumsForArtist(mContext, artistID);
    }

    @Override
    public Observable<List<Artist>> getAllArtists() {
        return ArtistLoader.getAllArtists(mContext);
    }

    @Override
    public Observable<Artist> getArtist(long artistID) {
        return ArtistLoader.getArtist(mContext, artistID);
    }

    @Override
    public Observable<List<Artist>> getArtists(String paramString) {
        return ArtistLoader.getArtists(mContext, paramString);
    }

    @Override
    public Observable<List<Song>> getSongsForArtist(long artistID) {
        return ArtistSongLoader.getSongsForArtist(mContext, artistID);
    }

    @Override
    public Observable<List<Song>> getRecentlyAddedSongs() {
        return LastAddedLoader.getLastAddedSongs(mContext);
    }

    @Override
    public Observable<List<Album>> getRecentlyAddedAlbums() {
        return LastAddedLoader.getLastAddedAlbums(mContext);
    }

    @Override
    public Observable<List<Artist>> getRecentlyAddedArtists() {
        return LastAddedLoader.getLastAddedArtist(mContext);
    }

    @Override
    public Observable<List<Song>> getRecentlyPlayedSongs() {
        return TopTracksLoader.getTopRecentSongs(mContext);
    }

    @Override
    public Observable<List<Album>> getRecentlyPlayedAlbums() {
        return AlbumLoader.getRecentlyPlayedAlbums(mContext);
    }

    @Override
    public Observable<List<Artist>> getRecentlyPlayedArtist() {
        return ArtistLoader.getRecentlyPlayedArtist(mContext);
    }

    @Override
    public Observable<List<Playlist>> getPlaylists(boolean defaultIncluded) {
        return PlaylistLoader.getPlaylists(mContext, defaultIncluded);
    }

    @Override
    public Observable<List<Song>> getSongsInPlaylist(long playlistID) {
        return PlaylistSongLoader.getSongsInPlaylist(mContext, playlistID);
    }

    @Override
    public Observable<List<Song>> getQueueSongs() {
        return QueueLoader.getQueueSongs(mContext);
    }

    @Override
    public Observable<List<Song>> getFavourateSongs() {
        return SongLoader.getFavoriteSong(mContext);
    }

    @Override
    public Observable<List<Album>> getFavourateAlbums() {
        return AlbumLoader.getFavourateAlbums(mContext);
    }

    @Override
    public Observable<List<Artist>> getFavourateArtist() {
        return ArtistLoader.getFavouriteArtists(mContext);
    }

    @Override
    public Observable<List<Song>> getAllSongs() {
        return SongLoader.getAllSongs(mContext);
    }

    @Override
    public Observable<List<Song>> searchSongs(String searchString) {
        return SongLoader.searchSongs(mContext, searchString);
    }

    @Override
    public Observable<List<Song>> getTopPlaySongs() {
        return TopTracksLoader.getTopPlaySongs(mContext);
    }

    @Override
    public Observable<List<FolderInfo>> getFoldersWithSong() {
        return FolderLoader.getFoldersWithSong(mContext);
    }

    @Override
    public Observable<List<Song>> getSongsInFolder(String path) {
        return SongLoader.getSongListInFolder(mContext, path);
    }

    @Override
    public Observable<List<Object>> getSearchResult(String queryString) {
        Observable<Song> songList = SongLoader.searchSongs(mContext, queryString)
                .flatMap(new Func1<List<Song>, Observable<Song>>() {
                    @Override
                    public Observable<Song> call(List<Song> songs) {
                        return Observable.from(songs);
                    }
                });

        Observable<Album> albumList = AlbumLoader.getAlbums(mContext, queryString)
                .flatMap(new Func1<List<Album>, Observable<Album>>() {
                    @Override
                    public Observable<Album> call(List<Album> albums) {
                        return Observable.from(albums);
                    }
                });

        Observable<Artist> artistList = ArtistLoader.getArtists(mContext, queryString)
                .flatMap(new Func1<List<Artist>, Observable<Artist>>() {
                    @Override
                    public Observable<Artist> call(List<Artist> artistList) {
                        return Observable.from(artistList);
                    }
                });

        Observable<String> songHeader = Observable.just(mContext.getString(R.string.songs));
        Observable<String> albumHeader = Observable.just(mContext.getString(R.string.albums));
        Observable<String> artistHeader = Observable.just(mContext.getString(R.string.artists));

        return Observable.merge(songHeader, songList, albumHeader, albumList, artistHeader, artistList).toList();
    }
}
