package zoker.demo.music.control.injector.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import zoker.demo.music.control.action.DbAction;
import zoker.demo.music.control.action.interfaces.DbRepository;

/**
 * Created by Zoker on 2017/2/24.
 */
@Module
public class DbModule {
    private Context context;

    public DbModule(Context context) {
        this.context = context;
    }

    @Provides
    public DbRepository proviteDbAction(){
        return new DbAction(context);
    }
}
