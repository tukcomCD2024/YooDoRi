package kr.ac.tukorea.whereareu.util.location;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl;
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor;
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor;
import kr.ac.tukorea.whereareu.util.sensor.LightSensor;
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor;

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
public final class LocationService_MembersInjector implements MembersInjector<LocationService> {
  private final Provider<DementiaHomeRepositoryImpl> repositoryProvider;

  private final Provider<AccelerometerSensor> accelerometerSensorProvider;

  private final Provider<MagneticFieldSensor> magneticFieldSensorProvider;

  private final Provider<GyroScopeSensor> gyroScopeSensorProvider;

  private final Provider<LightSensor> lightSensorProvider;

  public LocationService_MembersInjector(Provider<DementiaHomeRepositoryImpl> repositoryProvider,
      Provider<AccelerometerSensor> accelerometerSensorProvider,
      Provider<MagneticFieldSensor> magneticFieldSensorProvider,
      Provider<GyroScopeSensor> gyroScopeSensorProvider,
      Provider<LightSensor> lightSensorProvider) {
    this.repositoryProvider = repositoryProvider;
    this.accelerometerSensorProvider = accelerometerSensorProvider;
    this.magneticFieldSensorProvider = magneticFieldSensorProvider;
    this.gyroScopeSensorProvider = gyroScopeSensorProvider;
    this.lightSensorProvider = lightSensorProvider;
  }

  public static MembersInjector<LocationService> create(
      Provider<DementiaHomeRepositoryImpl> repositoryProvider,
      Provider<AccelerometerSensor> accelerometerSensorProvider,
      Provider<MagneticFieldSensor> magneticFieldSensorProvider,
      Provider<GyroScopeSensor> gyroScopeSensorProvider,
      Provider<LightSensor> lightSensorProvider) {
    return new LocationService_MembersInjector(repositoryProvider, accelerometerSensorProvider, magneticFieldSensorProvider, gyroScopeSensorProvider, lightSensorProvider);
  }

  @Override
  public void injectMembers(LocationService instance) {
    injectRepository(instance, repositoryProvider.get());
    injectAccelerometerSensor(instance, accelerometerSensorProvider.get());
    injectMagneticFieldSensor(instance, magneticFieldSensorProvider.get());
    injectGyroScopeSensor(instance, gyroScopeSensorProvider.get());
    injectLightSensor(instance, lightSensorProvider.get());
  }

  @InjectedFieldSignature("kr.ac.tukorea.whereareu.util.location.LocationService.repository")
  public static void injectRepository(LocationService instance,
      DementiaHomeRepositoryImpl repository) {
    instance.repository = repository;
  }

  @InjectedFieldSignature("kr.ac.tukorea.whereareu.util.location.LocationService.accelerometerSensor")
  public static void injectAccelerometerSensor(LocationService instance,
      AccelerometerSensor accelerometerSensor) {
    instance.accelerometerSensor = accelerometerSensor;
  }

  @InjectedFieldSignature("kr.ac.tukorea.whereareu.util.location.LocationService.magneticFieldSensor")
  public static void injectMagneticFieldSensor(LocationService instance,
      MagneticFieldSensor magneticFieldSensor) {
    instance.magneticFieldSensor = magneticFieldSensor;
  }

  @InjectedFieldSignature("kr.ac.tukorea.whereareu.util.location.LocationService.gyroScopeSensor")
  public static void injectGyroScopeSensor(LocationService instance,
      GyroScopeSensor gyroScopeSensor) {
    instance.gyroScopeSensor = gyroScopeSensor;
  }

  @InjectedFieldSignature("kr.ac.tukorea.whereareu.util.location.LocationService.lightSensor")
  public static void injectLightSensor(LocationService instance, LightSensor lightSensor) {
    instance.lightSensor = lightSensor;
  }
}
