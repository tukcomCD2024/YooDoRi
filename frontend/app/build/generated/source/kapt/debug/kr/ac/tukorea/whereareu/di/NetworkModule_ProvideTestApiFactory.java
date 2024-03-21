package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.LoginService;
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
public final class NetworkModule_ProvideTestApiFactory implements Factory<LoginService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideTestApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public LoginService get() {
    return provideTestApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideTestApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideTestApiFactory(retrofitProvider);
  }

  public static LoginService provideTestApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTestApi(retrofit));
  }
}
