package com.example.restoapp.dataclass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_resto_menu")
data class FavoriteRestoMenu(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    val deskripsi: String?,
    val harga: String?,
    val image: String?,
    val nama: String?
) : Parcelable