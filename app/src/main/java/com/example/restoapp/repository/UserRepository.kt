package com.example.restoapp.repository

import com.example.restoapp.model.RequestUser
import com.example.restoapp.networking.ApiUserServices

class UserRepository constructor(private val apiUserServices: ApiUserServices){
    fun getAllUser() = apiUserServices.getAllUser()
    fun addDataUser(reqUser : RequestUser) = apiUserServices.addDataUser(reqUser)
    fun updateDataUser(id : String, reqUser : RequestUser) = apiUserServices.updateDataUser(id, reqUser)
}