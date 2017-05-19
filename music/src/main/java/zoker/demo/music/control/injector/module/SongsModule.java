package zoker.demo.music.control.injector.module;

import com.zekers.utils.logger.Logger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zoker.demo.music.control.action.interfaces.DbRepository;
import zoker.demo.music.control.action.interfaces.KuGouRepository;
import zoker.demo.music.control.contract.SongsContract;
import zoker.demo.music.control.presenter.SongsPresenter;
import zoker.demo.music.control.usecase.GetSongs;

/**
 * 音乐模块
 * Created by Zoker on 2017/2/23.
 */
@Module
public class SongsModule {
    @Provides
    SongsContract.Presenter getSongsPresenter(GetSongs getSongs){
        Logger.d("$SongsModule #getSongsPresenter");
        return new SongsPresenter(getSongs);
    }

    @Provides
    GetSongs getSongsUsecase(KuGouRepository kuGouRepository, DbRepository dbrepository) {
        Logger.d("$SongsModule #SongsModule");
        return new GetSongs(dbrepository,kuGouRepository);
    }
}
