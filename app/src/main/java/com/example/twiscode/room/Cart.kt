package com.example.twiscode.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Cart")
@Parcelize
data class Cart(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "price")
    var price: String? = null,

    @ColumnInfo(name = "weight")
    var weight: String? = null,

    @ColumnInfo(name = "imgphoto")
    var imgPhoto: Int? = null,

    var jumlah: Int = 1,
): Parcelable