package ru.itis.liiceberg.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.auth_impl.navigation.AuthNavProviderImpl
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.ExploreNavProvider
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider
import ru.itis.liiceberg.common.navigation.SettingsNavProvider
import ru.itis.liiceberg.explore_impl.navigation.ExploreNavProviderImpl
import ru.itis.liiceberg.myplants_impl.navigation.MyPlantsNavProviderImpl
import ru.itis.liiceberg.settings_impl.navigation.SettingsNavProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindAuthNavProviderToAuthNavProviderImpl(impl: AuthNavProviderImpl): AuthNavProvider

    @Binds
    @Singleton
    fun bindExploreNavProviderToExploreNavProviderImpl(impl: ExploreNavProviderImpl): ExploreNavProvider

    @Binds
    @Singleton
    fun bindMyPlantsNavProviderToMyPlantsNavProviderImpl(impl: MyPlantsNavProviderImpl): MyPlantsNavProvider

    @Binds
    @Singleton
    fun bindSettingsNavProviderToSettingsNavProviderImpl(impl: SettingsNavProviderImpl): SettingsNavProvider

}