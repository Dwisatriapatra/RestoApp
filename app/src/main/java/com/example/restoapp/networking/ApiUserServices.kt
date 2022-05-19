package com.example.restoapp.networking

import com.example.restoapp.model.GetAllUserResponseItem
import com.example.restoapp.model.PostNewUser
import com.example.restoapp.model.RequestUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiUserServices {

    @GET("restouser")
    fun getAllUser() : Call<List<GetAllUserResponseItem>>

    @POST("restouser")
    fun addDataUser(@Body reqUser : RequestUser) : Call<PostNewUser>

    @PUT("restouser/{id}")
    fun updateDataUser(
        @Path("id") id : String,
        @Body request : RequestUser
    ) : Call<List<GetAllUserResponseItem>>

    companion object{
        var apiUserInterface : ApiUserServices? = null
        fun getInstance() : ApiUserServices{
            if(apiUserInterface == null){
                val baserUrl = "https://6254434c19bc53e2347b93f1.mockapi.io/"
                val retrofit = Retrofit.Builder()
                    .baseUrl(baserUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiUserInterface = retrofit.create(ApiUserServices::class.java)
            }
            return apiUserInterface!!
        }
    }
}