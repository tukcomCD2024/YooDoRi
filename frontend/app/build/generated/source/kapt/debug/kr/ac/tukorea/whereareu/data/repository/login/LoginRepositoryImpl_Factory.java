package kr.ac.tukorea.whereareu.data.repository.login;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.LoginService;

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
public final class LoginRepositoryImpl_Factory implements Factory<LoginRepositoryImpl> {
  private final Provider<LoginService> apiProvider;

  public LoginRepositoryImpl_Factory(Provider<LoginService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public LoginRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static LoginRepositoryImpl_Factory create(Provider<LoginService> apiProvider) {
    return new LoginRepositoryImpl_Factory(apiProvider);
  }

  public static LoginRepositoryImpl newInstance(LoginService api) {
    return new LoginRepositoryImpl(api);
  }
}
