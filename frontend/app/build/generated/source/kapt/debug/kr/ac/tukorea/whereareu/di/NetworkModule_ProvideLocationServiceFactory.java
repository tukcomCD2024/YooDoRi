package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kr.ac.tukorea.whereareu.util.location.LocationService;

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
public final class NetworkModule_ProvideLocationServiceFactory implements Factory<LocationService> {
  @Override
  public LocationService get() {
    return provideLocationService();
  }

  public static NetworkModule_ProvideLocationServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocationService provideLocationService() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideLocationService());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideLocationServiceFactory INSTANCE = new NetworkModule_ProvideLocationServiceFactory();
  }
}
