package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideDementiaHomeApiFactory implements Factory<DementiaHomeService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideDementiaHomeApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public DementiaHomeService get() {
    return provideDementiaHomeApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideDementiaHomeApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideDementiaHomeApiFactory(retrofitProvider);
  }

  public static DementiaHomeService provideDementiaHomeApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideDementiaHomeApi(retrofit));
  }
}