package ru.itis.liiceberg.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.liiceberg.auth.navigation.AuthNavProviderImpl
import ru.itis.liiceberg.common.navigation.AuthNavProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindAuthNavProviderToAuthNavProviderImpl(impl: AuthNavProviderImpl): AuthNavProvider

}