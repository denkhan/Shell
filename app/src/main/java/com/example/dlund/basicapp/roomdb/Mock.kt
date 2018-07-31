package com.example.dlund.basicapp.roomdb

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(primaryKeys = arrayOf("name"),
        indices = arrayOf((Index("name"))))

data class Mock(
        val name: String
)