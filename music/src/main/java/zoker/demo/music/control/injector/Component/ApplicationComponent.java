package zoker.demo.music.control.injector.Component;

import android.app.Application;

import javax.inject.Named;

import dagger.Component;
import retrofit2.Retrofit;
import zoker.demo.music.MusicApp;
import zoker.demo.music.control.injector.module.ApplicationModule;
import zoker.demo.music.control.injector.module.NetworkModule;
import zoker.demo.music.control.injector.scope.PerApplication;

/**
 * 理解为Component为桥梁，Module为规则
 * 此模块是整个项目的基础模块
 * Created by Zoker on 2017/2/23.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Application application();

    MusicApp musicApp();

    @Named("lastfm")
    Retrofit lastFMRetrofit();

    @Named("kugou")
    Retrofit kuGouRetrofit();
}
