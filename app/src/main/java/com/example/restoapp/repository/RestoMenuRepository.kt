package com.example.restoapp.repository

import com.example.restoapp.networking.ApiRestoMenuServices

class RestoMenuRepository constructor(private val apiRestoMenuServices: ApiRestoMenuServices) {
    fun getALlRestoMenu() = apiRestoMenuServices.getAllRestoMenu()
}