package zoker.demo.music;

import com.zekers.pjutils.BaseApplication;
import com.zekers.utils.logger.Logger;
import com.zekers.utils.permission.PermissionManager;

import zoker.demo.music.control.injector.Component.ApplicationComponent;
import zoker.demo.music.control.injector.Component.DaggerApplicationComponent;
import zoker.demo.music.control.injector.module.ApplicationModule;
import zoker.demo.music.control.injector.module.NetworkModule;

/**
 * Application
 * Created by Zoker on 2017/2/23.
 */

public class MusicApp extends BaseApplication{

    private ApplicationComponent applicationComponent;
    private boolean debug=true;
    private String tag="com.zoker.Music";
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(debug,tag);
        setupInjector();
        PermissionManager.init(this);
    }

    /**
     * 依赖注入
     */
    public void setupInjector(){
        applicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
