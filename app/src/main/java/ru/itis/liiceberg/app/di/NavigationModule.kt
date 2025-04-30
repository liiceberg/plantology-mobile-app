package ru.itis.liiceberg.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.auth_impl.presentation.navigation.AuthNavProviderImpl
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.ExploreNavProvider
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider
import ru.itis.liiceberg.common.navigation.ReminderNavProvider
import ru.itis.liiceberg.common.navigation.ScheduleNavProvider
import ru.itis.liiceberg.common.navigation.SettingsNavProvider
import ru.itis.liiceberg.explore_impl.presentation.navigation.ExploreNavProviderImpl
import ru.itis.liiceberg.myplants_impl.presentation.navigation.MyPlantsNavProviderImpl
import ru.itis.liiceberg.reminder_impl.presentation.navigation.ReminderNavProviderImpl
import ru.itis.liiceberg.schedule_impl.presentation.navigation.ScheduleNavProviderImpl
import ru.itis.liiceberg.settings_impl.presentation.navigation.SettingsNavProviderImpl
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
    
    @Binds
    @Singleton
    fun bindReminderNavProviderToReminderNavProviderImpl(impl: ReminderNavProviderImpl): ReminderNavProvider

    @Binds
    @Singleton
    fun bindScheduleNavProviderToScheduleNavProviderImpl(impl: ScheduleNavProviderImpl): ScheduleNavProvider

}