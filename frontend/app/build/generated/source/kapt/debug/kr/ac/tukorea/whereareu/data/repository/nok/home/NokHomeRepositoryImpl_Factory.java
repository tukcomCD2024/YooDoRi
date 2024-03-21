package kr.ac.tukorea.whereareu.data.repository.nok.home;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.nok.NokHomeService;

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
public final class NokHomeRepositoryImpl_Factory implements Factory<NokHomeRepositoryImpl> {
  private final Provider<NokHomeService> apiProvider;

  public NokHomeRepositoryImpl_Factory(Provider<NokHomeService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public NokHomeRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static NokHomeRepositoryImpl_Factory create(Provider<NokHomeService> apiProvider) {
    return new NokHomeRepositoryImpl_Factory(apiProvider);
  }

  public static NokHomeRepositoryImpl newInstance(NokHomeService api) {
    return new NokHomeRepositoryImpl(api);
  }
}
