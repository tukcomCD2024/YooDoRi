package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepository;

@ScopeMetadata("dagger.hilt.android.scopes.ViewModelScoped")
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
public final class RepositoryModule_ProvidesHomeRepositoryFactory implements Factory<DementiaHomeRepository> {
  private final Provider<DementiaHomeService> dementiaHomeServiceProvider;

  public RepositoryModule_ProvidesHomeRepositoryFactory(
      Provider<DementiaHomeService> dementiaHomeServiceProvider) {
    this.dementiaHomeServiceProvider = dementiaHomeServiceProvider;
  }

  @Override
  public DementiaHomeRepository get() {
    return providesHomeRepository(dementiaHomeServiceProvider.get());
  }

  public static RepositoryModule_ProvidesHomeRepositoryFactory create(
      Provider<DementiaHomeService> dementiaHomeServiceProvider) {
    return new RepositoryModule_ProvidesHomeRepositoryFactory(dementiaHomeServiceProvider);
  }

  public static DementiaHomeRepository providesHomeRepository(
      DementiaHomeService dementiaHomeService) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providesHomeRepository(dementiaHomeService));
  }
}
