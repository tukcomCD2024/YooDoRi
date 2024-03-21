package kr.ac.tukorea.whereareu.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.WhereAreUApplication;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import kr.ac.tukorea.whereareu.data.api.LoginService;
import kr.ac.tukorea.whereareu.data.api.nok.NokHomeService;
import kr.ac.tukorea.whereareu.util.location.LocationService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000fH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tH\u0007J\u001a\u0010\u0014\u001a\u0002H\u0015\"\u0006\b\u0000\u0010\u0015\u0018\u0001*\u00020\tH\u0082\b\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lkr/ac/tukorea/whereareu/di/NetworkModule;", "", "()V", "NETWORK_EXCEPTION_BODY_IS_NULL", "", "NETWORK_EXCEPTION_OFFLINE_CASE", "provideDementiaHomeApi", "Lkr/ac/tukorea/whereareu/data/api/dementia/DementiaHomeService;", "retrofit", "Lretrofit2/Retrofit;", "provideLocationService", "Lkr/ac/tukorea/whereareu/util/location/LocationService;", "provideNokHomeApi", "Lkr/ac/tukorea/whereareu/data/api/nok/NokHomeService;", "provideOKHttpClient", "Lokhttp3/OkHttpClient;", "provideRetrofit", "okHttpClient", "provideTestApi", "Lkr/ac/tukorea/whereareu/data/api/LoginService;", "buildService", "T", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NETWORK_EXCEPTION_OFFLINE_CASE = "network status is offline";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NETWORK_EXCEPTION_BODY_IS_NULL = "result body is null";
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOKHttpClient() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.api.LoginService provideTestApi(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService provideDementiaHomeApi(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.api.nok.NokHomeService provideNokHomeApi(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.location.LocationService provideLocationService() {
        return null;
    }
}