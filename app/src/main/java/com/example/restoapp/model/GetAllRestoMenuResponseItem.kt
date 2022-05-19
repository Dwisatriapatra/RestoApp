package com.example.restoapp.model

import java.io.Serializable

data class GetAllRestoMenuResponseItem(
    val deskripsi: String,
    val harga: String,
    val id: String,
    val image: String,
    val nama: String
) : Serializable