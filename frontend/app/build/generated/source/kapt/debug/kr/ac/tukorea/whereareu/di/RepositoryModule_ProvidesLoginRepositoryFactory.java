package kr.ac.tukorea.whereareu.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kr.ac.tukorea.whereareu.data.api.LoginService;
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepository;

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
public final class RepositoryModule_ProvidesLoginRepositoryFactory implements Factory<LoginRepository> {
  private final Provider<LoginService> loginServiceProvider;

  public RepositoryModule_ProvidesLoginRepositoryFactory(
      Provider<LoginService> loginServiceProvider) {
    this.loginServiceProvider = loginServiceProvider;
  }

  @Override
  public LoginRepository get() {
    return providesLoginRepository(loginServiceProvider.get());
  }

  public static RepositoryModule_ProvidesLoginRepositoryFactory create(
      Provider<LoginService> loginServiceProvider) {
    return new RepositoryModule_ProvidesLoginRepositoryFactory(loginServiceProvider);
  }

  public static LoginRepository providesLoginRepository(LoginService loginService) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providesLoginRepository(loginService));
  }
}
