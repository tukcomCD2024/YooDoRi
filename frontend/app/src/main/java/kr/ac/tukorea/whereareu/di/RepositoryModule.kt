package kr.ac.tukorea.whereareu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.tukorea.whereareu.data.api.NokHomeService
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.repository.home.DementiaHomeRepository
import kr.ac.tukorea.whereareu.data.repository.home.DementiaHomeRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepository
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesLoginRepository(
        loginService: LoginService
    ): LoginRepository = LoginRepositoryImpl(loginService)

    @ViewModelScoped
    @Provides
    fun providesHomeRepository(
        nokHomeService: NokHomeService
    ): DementiaHomeRepository = DementiaHomeRepositoryImpl(nokHomeService)
}