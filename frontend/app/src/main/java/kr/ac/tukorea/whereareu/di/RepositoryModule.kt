package kr.ac.tukorea.whereareu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.ac.tukorea.whereareu.data.api.dementia.DementiaHomeService
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.api.ModifyUserInfoService
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepository
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepository
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.setting.SettingRepository
import kr.ac.tukorea.whereareu.data.repository.setting.SettingRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

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
        dementiaHomeService: DementiaHomeService
    ): DementiaHomeRepository = DementiaHomeRepositoryImpl(dementiaHomeService)

    @ViewModelScoped
    @Provides
    fun providesSettingRepository(
        modifyUserInfoService: ModifyUserInfoService
    ): SettingRepository = SettingRepositoryImpl(modifyUserInfoService)
}