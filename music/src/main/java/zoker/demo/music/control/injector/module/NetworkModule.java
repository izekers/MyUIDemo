package zoker.demo.music.control.injector.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zekers.network.base.HandlerInterceptor;
import com.zekers.utils.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zoker.demo.music.Constants;
import zoker.demo.music.MusicApp;
import zoker.demo.music.control.injector.scope.PerActivity;
import zoker.demo.music.control.injector.scope.PerApplication;

/**
 * 这块我要尝试分离出来
 * Created by Zoker on 2017/2/23.
 */
@Module
public class NetworkModule {
    private final MusicApp musicApp;

    public NetworkModule(MusicApp musicApp) {
        this.musicApp = musicApp;
    }

    //@Name应该是提供别名，防止返回方法相同造成不知道怎么返回
    @Provides
    @Named("lastfm")
    @PerApplication
    Retrofit provideLastFMRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        Logger.d("$NetworkModule #provideLastFMRetrofit");
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL_LASTFM)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    //@Name应该是提供别名，防止返回方法相同造成不知道怎么返回
    @Provides
    @Named("kugou")
    @PerApplication
    Retrofit provideKuGouRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        Logger.d("$NetworkModule #provideLastFMRetrofit");
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL_KUGOU)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        Logger.d("$NetworkModule #provideGsonConverterFactory");
        Gson gson = new GsonBuilder().create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    OkHttpClient provideClient() {
        Logger.d("$NetworkModule #provideClient");
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
//                .cache(new Cache(FileUtil.getHttpCacheDir(MusicApp.Instance), Constants.HTTP_CACHE_SIZE))
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HandlerInterceptor())
                .readTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }
}
