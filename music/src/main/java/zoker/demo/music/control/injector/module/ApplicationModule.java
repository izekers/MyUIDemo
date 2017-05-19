package zoker.demo.music.control.injector.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import zoker.demo.music.MusicApp;
import zoker.demo.music.control.injector.scope.PerApplication;

/**
 * Module方法规定注入规则，这里是初始化的Module
 * 程序初始化支持
 * Created by Zoker on 2017/2/23.
 */
@Module
public class ApplicationModule {
    private final MusicApp musicApp;

    public ApplicationModule(MusicApp musicApp) {
        this.musicApp = musicApp;
    }

    @Provides
    @PerApplication
    public MusicApp provideMusicApp(){
        return musicApp;
    }
    @Provides
    @PerApplication
    public Application provideApplication(){
        return musicApp;
    }
}
