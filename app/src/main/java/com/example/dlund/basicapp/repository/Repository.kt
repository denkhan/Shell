package com.example.dlund.basicapp.repository

import com.example.dlund.basicapp.roomdb.Mock
import io.reactivex.Flowable

interface Repository{

    fun getMocks(name: String) : Flowable<List<Mock>>
}