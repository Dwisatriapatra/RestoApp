package com.example.restoapp.model

import java.io.Serializable

data class GetAllUserResponseItem(
    val id: String,
    val image: String,
    val password: String,
    val position: String,
    val username: String
) : Serializable