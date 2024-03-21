package kr.ac.tukorea.whereareu.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor;

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
public final class SensorModule_ProvideMagneticFieldSensorFactory implements Factory<MagneticFieldSensor> {
  private final Provider<Application> appProvider;

  public SensorModule_ProvideMagneticFieldSensorFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public MagneticFieldSensor get() {
    return provideMagneticFieldSensor(appProvider.get());
  }

  public static SensorModule_ProvideMagneticFieldSensorFactory create(
      Provider<Application> appProvider) {
    return new SensorModule_ProvideMagneticFieldSensorFactory(appProvider);
  }

  public static MagneticFieldSensor provideMagneticFieldSensor(Application app) {
    return Preconditions.checkNotNullFromProvides(SensorModule.INSTANCE.provideMagneticFieldSensor(app));
  }
}
