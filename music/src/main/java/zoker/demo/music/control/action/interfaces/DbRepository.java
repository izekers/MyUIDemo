package zoker.demo.music.control.action.interfaces;

import java.util.List;

import rx.Observable;
import zoker.demo.music.model.Album;
import zoker.demo.music.model.Artist;
import zoker.demo.music.model.FolderInfo;
import zoker.demo.music.model.Playlist;
import zoker.demo.music.model.Song;

/**
 * Created by Zoker on 2017/2/24.
 */

public interface DbRepository {
    //form local

    Observable<List<Album>> getAllAlbums();

    Observable<Album> getAlbum(long id);

    Observable<List<Album>> getAlbums(String paramString);

    Observable<List<Song>> getSongsForAlbum(long albumID);

    Observable<List<Album>> getAlbumsForArtist(long artistID);

    Observable<List<Artist>> getAllArtists();

    Observable<Artist> getArtist(long artistID);

    Observable<List<Artist>> getArtists(String paramString);

    Observable<List<Song>> getSongsForArtist(long artistID);

    Observable<List<Song>> getRecentlyAddedSongs();

    Observable<List<Album>> getRecentlyAddedAlbums();

    Observable<List<Artist>> getRecentlyAddedArtists();

    Observable<List<Song>> getRecentlyPlayedSongs();

    Observable<List<Album>> getRecentlyPlayedAlbums();

    Observable<List<Artist>> getRecentlyPlayedArtist();

    Observable<List<Playlist>> getPlaylists(boolean defaultIncluded);

    Observable<List<Song>> getSongsInPlaylist(long playlistID);

    Observable<List<Song>> getQueueSongs();

    Observable<List<Song>> getFavourateSongs();

    Observable<List<Album>> getFavourateAlbums();

    Observable<List<Artist>> getFavourateArtist();

    Observable<List<Song>> getAllSongs();

    Observable<List<Song>> searchSongs(String searchString);

    Observable<List<Song>> getTopPlaySongs();

    Observable<List<FolderInfo>> getFoldersWithSong();

    Observable<List<Song>> getSongsInFolder(String path);

    Observable<List<Object>> getSearchResult(String queryString);
}
