package com.example.dlund.basicapp.di

import android.content.Context
import com.example.dlund.basicapp.roomdb.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides @Singleton fun provideRoomCurrencyDataSource(context: Context) =
            AppDatabase.buildPersistentMedia(context)
}