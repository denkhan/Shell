package com.example.dlund.basicapp.roomdb

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface MockDao{
    @Query("SELECT * FROM mock " +
            "WHERE name = :name")
    fun get(name: String): Flowable<List<Mock>>

    @Update
    fun update(mock: Mock)

    @Insert
    fun insert(mock: Mock)

    @Delete
    fun delete(mock: Mock)
}