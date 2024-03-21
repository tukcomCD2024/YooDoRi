package kr.ac.tukorea.whereareu.data.repository.dementia.home;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;

@ScopeMetadata
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
public final class DementiaHomeRepositoryImpl_Factory implements Factory<DementiaHomeRepositoryImpl> {
  private final Provider<DementiaHomeService> apiProvider;

  public DementiaHomeRepositoryImpl_Factory(Provider<DementiaHomeService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public DementiaHomeRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static DementiaHomeRepositoryImpl_Factory create(
      Provider<DementiaHomeService> apiProvider) {
    return new DementiaHomeRepositoryImpl_Factory(apiProvider);
  }

  public static DementiaHomeRepositoryImpl newInstance(DementiaHomeService api) {
    return new DementiaHomeRepositoryImpl(api);
  }
}
