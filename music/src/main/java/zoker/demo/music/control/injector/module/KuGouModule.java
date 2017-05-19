package zoker.demo.music.control.injector.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zoker.demo.music.control.action.KuGouAction;
import zoker.demo.music.control.action.interfaces.KuGouRepository;

/**
 * Created by Zoker on 2017/2/24.
 */
@Module
public class KuGouModule {
    @Provides
    KuGouRepository provideKugouAction(@Named("kugou") Retrofit repository){
        return new KuGouAction(repository);
    }
}
