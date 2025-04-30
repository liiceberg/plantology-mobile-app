package ru.itis.liiceberg.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.app.navigation.Navigator
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.ExploreNavProvider
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider
import ru.itis.liiceberg.common.navigation.ReminderNavProvider
import ru.itis.liiceberg.common.navigation.ScheduleNavProvider
import ru.itis.liiceberg.common.navigation.SettingsNavProvider

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigator(
        auth: AuthNavProvider,
        explore: ExploreNavProvider,
        myPlants: MyPlantsNavProvider,
        settings: SettingsNavProvider,
        reminder: ReminderNavProvider,
        schedule: ScheduleNavProvider,
    ): Navigator {
        return Navigator(auth, explore, myPlants, settings, reminder, schedule)
    }

}