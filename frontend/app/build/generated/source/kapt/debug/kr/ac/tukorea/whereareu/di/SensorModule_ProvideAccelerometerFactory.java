package kr.ac.tukorea.whereareu.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor;

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
public final class SensorModule_ProvideAccelerometerFactory implements Factory<AccelerometerSensor> {
  private final Provider<Application> appProvider;

  public SensorModule_ProvideAccelerometerFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public AccelerometerSensor get() {
    return provideAccelerometer(appProvider.get());
  }

  public static SensorModule_ProvideAccelerometerFactory create(Provider<Application> appProvider) {
    return new SensorModule_ProvideAccelerometerFactory(appProvider);
  }

  public static AccelerometerSensor provideAccelerometer(Application app) {
    return Preconditions.checkNotNullFromProvides(SensorModule.INSTANCE.provideAccelerometer(app));
  }
}
