package kr.ac.tukorea.whereareu.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor;

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
public final class SensorModule_ProvideGyroScopeSensorFactory implements Factory<GyroScopeSensor> {
  private final Provider<Application> appProvider;

  public SensorModule_ProvideGyroScopeSensorFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public GyroScopeSensor get() {
    return provideGyroScopeSensor(appProvider.get());
  }

  public static SensorModule_ProvideGyroScopeSensorFactory create(
      Provider<Application> appProvider) {
    return new SensorModule_ProvideGyroScopeSensorFactory(appProvider);
  }

  public static GyroScopeSensor provideGyroScopeSensor(Application app) {
    return Preconditions.checkNotNullFromProvides(SensorModule.INSTANCE.provideGyroScopeSensor(app));
  }
}
