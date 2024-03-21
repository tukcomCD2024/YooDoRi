package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.nok.NokHomeService;
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
public final class NetworkModule_ProvideNokHomeApiFactory implements Factory<NokHomeService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideNokHomeApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public NokHomeService get() {
    return provideNokHomeApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideNokHomeApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideNokHomeApiFactory(retrofitProvider);
  }

  public static NokHomeService provideNokHomeApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideNokHomeApi(retrofit));
  }
}
