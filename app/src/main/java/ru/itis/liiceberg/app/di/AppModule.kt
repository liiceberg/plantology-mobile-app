package ru.itis.liiceberg.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.app.navigation.Navigator
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import ru.itis.liiceberg.common.navigation.MyPlantsNavProvider

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigator(auth: AuthNavProvider, myPlants: MyPlantsNavProvider): Navigator {
        return Navigator(auth, myPlants)
    }

}