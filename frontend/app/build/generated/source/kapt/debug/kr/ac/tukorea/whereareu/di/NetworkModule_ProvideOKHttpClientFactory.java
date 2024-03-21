package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideOKHttpClientFactory implements Factory<OkHttpClient> {
  @Override
  public OkHttpClient get() {
    return provideOKHttpClient();
  }

  public static NetworkModule_ProvideOKHttpClientFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OkHttpClient provideOKHttpClient() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOKHttpClient());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideOKHttpClientFactory INSTANCE = new NetworkModule_ProvideOKHttpClientFactory();
  }
}
