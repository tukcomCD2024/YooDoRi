package kr.ac.tukorea.whereareu.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.util.sensor.LightSensor;

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
public final class SensorModule_ProvideLightSensorFactory implements Factory<LightSensor> {
  private final Provider<Application> appProvider;

  public SensorModule_ProvideLightSensorFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public LightSensor get() {
    return provideLightSensor(appProvider.get());
  }

  public static SensorModule_ProvideLightSensorFactory create(Provider<Application> appProvider) {
    return new SensorModule_ProvideLightSensorFactory(appProvider);
  }

  public static LightSensor provideLightSensor(Application app) {
    return Preconditions.checkNotNullFromProvides(SensorModule.INSTANCE.provideLightSensor(app));
  }
}
