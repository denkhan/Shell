package com.example.dlund.basicapp.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {
    //fun inject(viewModel: MediaViewModel)
}