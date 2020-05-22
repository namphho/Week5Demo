package com.hnam.week5demo.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String,
    var description: String,
    var avatar: Int
) : Parcelable {

    constructor() : this(null, "", "", -1)
}