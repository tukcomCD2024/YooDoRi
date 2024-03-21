package kr.ac.tukorea.whereareu.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService;
import kr.ac.tukorea.whereareu.data.api.LoginService;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepository;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl;
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepository;
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lkr/ac/tukorea/whereareu/di/RepositoryModule;", "", "()V", "providesHomeRepository", "Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepository;", "dementiaHomeService", "Lkr/ac/tukorea/whereareu/data/api/dementia/DementiaHomeService;", "providesLoginRepository", "Lkr/ac/tukorea/whereareu/data/repository/login/LoginRepository;", "loginService", "Lkr/ac/tukorea/whereareu/data/api/LoginService;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ViewModelComponent.class})
public final class RepositoryModule {
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.di.RepositoryModule INSTANCE = null;
    
    private RepositoryModule() {
        super();
    }
    
    @dagger.Provides()
    @dagger.hilt.android.scopes.ViewModelScoped()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.repository.login.LoginRepository providesLoginRepository(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.api.LoginService loginService) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.hilt.android.scopes.ViewModelScoped()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepository providesHomeRepository(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService dementiaHomeService) {
        return null;
    }
}