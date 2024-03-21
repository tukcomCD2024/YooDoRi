package kr.ac.tukorea.whereareu.presentation.nok.home;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl;

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
public final class NokHomeViewModel_Factory implements Factory<NokHomeViewModel> {
  private final Provider<NokHomeRepositoryImpl> repositoryProvider;

  public NokHomeViewModel_Factory(Provider<NokHomeRepositoryImpl> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public NokHomeViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static NokHomeViewModel_Factory create(
      Provider<NokHomeRepositoryImpl> repositoryProvider) {
    return new NokHomeViewModel_Factory(repositoryProvider);
  }

  public static NokHomeViewModel newInstance(NokHomeRepositoryImpl repository) {
    return new NokHomeViewModel(repository);
  }
}
