package ru.itis.liiceberg.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.common.navigation.AuthNavProvider

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigator(auth: AuthNavProvider) : Navigator = Navigator(auth)

}