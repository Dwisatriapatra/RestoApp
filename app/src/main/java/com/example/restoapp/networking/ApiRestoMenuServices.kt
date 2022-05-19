package com.example.restoapp.networking

import com.example.restoapp.model.GetAllRestoMenuResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiRestoMenuServices {

    @GET("restomenu")
    fun getAllRestoMenu() : Call<List<GetAllRestoMenuResponseItem>>

    companion object{
        var apiRestoMenuServices : ApiRestoMenuServices? = null
        fun getInstance() : ApiRestoMenuServices{
            if(apiRestoMenuServices== null){
                val baserUrl = "https://6254434c19bc53e2347b93f1.mockapi.io/"
                val retrofit = Retrofit.Builder()
                    .baseUrl(baserUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiRestoMenuServices = retrofit.create(ApiRestoMenuServices::class.java)
            }
            return apiRestoMenuServices!!
        }
    }
}