package zoker.demo.music.control.injector.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import zoker.demo.music.control.injector.scope.PerActivity;

/**
 * Created by Zoker on 2017/2/23.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Context provideContext(){
        return activity;
    }
}
