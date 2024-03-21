package kr.ac.tukorea.whereareu;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.LoginService;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import kr.ac.tukorea.whereareu.data.api.nok.NokHomeService;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl;
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl;
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl;
import kr.ac.tukorea.whereareu.di.NetworkModule;
import kr.ac.tukorea.whereareu.di.NetworkModule_ProvideDementiaHomeApiFactory;
import kr.ac.tukorea.whereareu.di.NetworkModule_ProvideNokHomeApiFactory;
import kr.ac.tukorea.whereareu.di.NetworkModule_ProvideOKHttpClientFactory;
import kr.ac.tukorea.whereareu.di.NetworkModule_ProvideRetrofitFactory;
import kr.ac.tukorea.whereareu.di.NetworkModule_ProvideTestApiFactory;
import kr.ac.tukorea.whereareu.di.SensorModule;
import kr.ac.tukorea.whereareu.di.SensorModule_ProvideAccelerometerFactory;
import kr.ac.tukorea.whereareu.di.SensorModule_ProvideGyroScopeSensorFactory;
import kr.ac.tukorea.whereareu.di.SensorModule_ProvideLightSensorFactory;
import kr.ac.tukorea.whereareu.di.SensorModule_ProvideMagneticFieldSensorFactory;
import kr.ac.tukorea.whereareu.presentation.dementia.DementiaMainActivity;
import kr.ac.tukorea.whereareu.presentation.dementia.setting.DementiaSettingFragment;
import kr.ac.tukorea.whereareu.presentation.login.LoginActivity;
import kr.ac.tukorea.whereareu.presentation.login.LoginFragment;
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel;
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel_HiltModules_KeyModule_ProvideFactory;
import kr.ac.tukorea.whereareu.presentation.login.SplashActivity;
import kr.ac.tukorea.whereareu.presentation.login.dementia.PatientIdentifyFragment;
import kr.ac.tukorea.whereareu.presentation.login.dementia.PatientOtpFragment;
import kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragment;
import kr.ac.tukorea.whereareu.presentation.login.nok.NokOtpFragment;
import kr.ac.tukorea.whereareu.presentation.nok.NokMainActivity;
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeFragment;
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel;
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel_HiltModules_KeyModule_ProvideFactory;
import kr.ac.tukorea.whereareu.presentation.nok.home.PredictLocationFragment;
import kr.ac.tukorea.whereareu.util.location.LocationService;
import kr.ac.tukorea.whereareu.util.location.LocationService_MembersInjector;
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor;
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor;
import kr.ac.tukorea.whereareu.util.sensor.LightSensor;
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerWhereAreUApplication_HiltComponents_SingletonC {
  private DaggerWhereAreUApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(
        HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
      Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder networkModule(NetworkModule networkModule) {
      Preconditions.checkNotNull(networkModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder sensorModule(SensorModule sensorModule) {
      Preconditions.checkNotNull(sensorModule);
      return this;
    }

    public WhereAreUApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements WhereAreUApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonCImpl);
    }
  }

  private static final class ActivityCBuilder implements WhereAreUApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements WhereAreUApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements WhereAreUApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements WhereAreUApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements WhereAreUApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements WhereAreUApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public WhereAreUApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends WhereAreUApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends WhereAreUApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }

    @Override
    public void injectDementiaSettingFragment(DementiaSettingFragment arg0) {
    }

    @Override
    public void injectLoginFragment(LoginFragment arg0) {
    }

    @Override
    public void injectPatientIdentifyFragment(PatientIdentifyFragment arg0) {
    }

    @Override
    public void injectPatientOtpFragment(PatientOtpFragment arg0) {
    }

    @Override
    public void injectNokIdentityFragment(NokIdentityFragment arg0) {
    }

    @Override
    public void injectNokOtpFragment(NokOtpFragment arg0) {
    }

    @Override
    public void injectNokHomeFragment(NokHomeFragment arg0) {
    }

    @Override
    public void injectPredictLocationFragment(PredictLocationFragment arg0) {
    }
  }

  private static final class ViewCImpl extends WhereAreUApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends WhereAreUApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(2).add(LoginViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(NokHomeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public void injectDementiaMainActivity(DementiaMainActivity arg0) {
    }

    @Override
    public void injectLoginActivity(LoginActivity arg0) {
    }

    @Override
    public void injectSplashActivity(SplashActivity arg0) {
    }

    @Override
    public void injectNokMainActivity(NokMainActivity arg0) {
    }
  }

  private static final class ViewModelCImpl extends WhereAreUApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<NokHomeViewModel> nokHomeViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private LoginRepositoryImpl loginRepositoryImpl() {
      return new LoginRepositoryImpl(singletonCImpl.provideTestApiProvider.get());
    }

    private NokHomeRepositoryImpl nokHomeRepositoryImpl() {
      return new NokHomeRepositoryImpl(singletonCImpl.provideNokHomeApiProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.nokHomeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, Provider<ViewModel>>newMapBuilder(2).put("kr.ac.tukorea.whereareu.presentation.login.LoginViewModel", ((Provider) loginViewModelProvider)).put("kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel", ((Provider) nokHomeViewModelProvider)).build();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // kr.ac.tukorea.whereareu.presentation.login.LoginViewModel 
          return (T) new LoginViewModel(viewModelCImpl.loginRepositoryImpl());

          case 1: // kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel 
          return (T) new NokHomeViewModel(viewModelCImpl.nokHomeRepositoryImpl());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends WhereAreUApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends WhereAreUApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    private DementiaHomeRepositoryImpl dementiaHomeRepositoryImpl() {
      return new DementiaHomeRepositoryImpl(singletonCImpl.provideDementiaHomeApiProvider.get());
    }

    @Override
    public void injectLocationService(LocationService arg0) {
      injectLocationService2(arg0);
    }

    private LocationService injectLocationService2(LocationService instance) {
      LocationService_MembersInjector.injectRepository(instance, dementiaHomeRepositoryImpl());
      LocationService_MembersInjector.injectAccelerometerSensor(instance, singletonCImpl.provideAccelerometerProvider.get());
      LocationService_MembersInjector.injectMagneticFieldSensor(instance, singletonCImpl.provideMagneticFieldSensorProvider.get());
      LocationService_MembersInjector.injectGyroScopeSensor(instance, singletonCImpl.provideGyroScopeSensorProvider.get());
      LocationService_MembersInjector.injectLightSensor(instance, singletonCImpl.provideLightSensorProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends WhereAreUApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOKHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<LoginService> provideTestApiProvider;

    private Provider<NokHomeService> provideNokHomeApiProvider;

    private Provider<DementiaHomeService> provideDementiaHomeApiProvider;

    private Provider<AccelerometerSensor> provideAccelerometerProvider;

    private Provider<MagneticFieldSensor> provideMagneticFieldSensorProvider;

    private Provider<GyroScopeSensor> provideGyroScopeSensorProvider;

    private Provider<LightSensor> provideLightSensorProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOKHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 2));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 1));
      this.provideTestApiProvider = DoubleCheck.provider(new SwitchingProvider<LoginService>(singletonCImpl, 0));
      this.provideNokHomeApiProvider = DoubleCheck.provider(new SwitchingProvider<NokHomeService>(singletonCImpl, 3));
      this.provideDementiaHomeApiProvider = DoubleCheck.provider(new SwitchingProvider<DementiaHomeService>(singletonCImpl, 4));
      this.provideAccelerometerProvider = DoubleCheck.provider(new SwitchingProvider<AccelerometerSensor>(singletonCImpl, 5));
      this.provideMagneticFieldSensorProvider = DoubleCheck.provider(new SwitchingProvider<MagneticFieldSensor>(singletonCImpl, 6));
      this.provideGyroScopeSensorProvider = DoubleCheck.provider(new SwitchingProvider<GyroScopeSensor>(singletonCImpl, 7));
      this.provideLightSensorProvider = DoubleCheck.provider(new SwitchingProvider<LightSensor>(singletonCImpl, 8));
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @Override
    public void injectWhereAreUApplication(WhereAreUApplication whereAreUApplication) {
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // kr.ac.tukorea.whereareu.data.api.LoginService 
          return (T) NetworkModule_ProvideTestApiFactory.provideTestApi(singletonCImpl.provideRetrofitProvider.get());

          case 1: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOKHttpClientProvider.get());

          case 2: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOKHttpClientFactory.provideOKHttpClient();

          case 3: // kr.ac.tukorea.whereareu.data.api.nok.NokHomeService 
          return (T) NetworkModule_ProvideNokHomeApiFactory.provideNokHomeApi(singletonCImpl.provideRetrofitProvider.get());

          case 4: // kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService 
          return (T) NetworkModule_ProvideDementiaHomeApiFactory.provideDementiaHomeApi(singletonCImpl.provideRetrofitProvider.get());

          case 5: // kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor 
          return (T) SensorModule_ProvideAccelerometerFactory.provideAccelerometer(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 6: // kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor 
          return (T) SensorModule_ProvideMagneticFieldSensorFactory.provideMagneticFieldSensor(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 7: // kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor 
          return (T) SensorModule_ProvideGyroScopeSensorFactory.provideGyroScopeSensor(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 8: // kr.ac.tukorea.whereareu.util.sensor.LightSensor 
          return (T) SensorModule_ProvideLightSensorFactory.provideLightSensor(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
