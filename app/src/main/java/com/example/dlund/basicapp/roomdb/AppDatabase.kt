package com.example.dlund.basicapp.roomdb

import android.content.Context
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

@Database(
        version = 1,
        entities = arrayOf(Mock::class)
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mockDao(): MockDao

    companion object {
        fun buildPersistentMedia(context: Context): AppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "mock.db"
        )
                .fallbackToDestructiveMigration()
                .build()
    }
}