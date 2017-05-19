package zoker.demo.music.control.injector.Component;

import dagger.Component;
import zoker.demo.music.control.injector.module.ActivityModule;
import zoker.demo.music.control.injector.module.DbModule;
import zoker.demo.music.control.injector.module.KuGouModule;
import zoker.demo.music.control.injector.module.SongsModule;
import zoker.demo.music.control.injector.scope.PerActivity;
import zoker.demo.music.view.fragment.SongsFragment;

/**
 * 建立桥梁，依赖AppComponent
 * Created by Zoker on 2017/2/23.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, SongsModule.class, KuGouModule.class, DbModule.class})
public interface SongsComponent{
    void inject(SongsFragment songsFragment);
}
