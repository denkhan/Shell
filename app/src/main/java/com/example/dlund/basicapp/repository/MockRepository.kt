package com.example.dlund.basicapp.repository

import com.example.dlund.basicapp.roomdb.AppDatabase
import com.example.dlund.basicapp.roomdb.Mock
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockRepository @Inject constructor(
        private val dataSource: AppDatabase
) : Repository{
    override fun getMocks(name: String): Flowable<List<Mock>> {
        return dataSource.mockDao().get(name)
    }
}