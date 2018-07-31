package com.example.dlund.basicapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val context: App) {

    @Provides @Singleton fun provideContext(): Context = context

}