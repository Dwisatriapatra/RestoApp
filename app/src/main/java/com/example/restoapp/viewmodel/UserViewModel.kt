package com.example.restoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restoapp.model.GetAllUserResponseItem
import com.example.restoapp.model.PostNewUser
import com.example.restoapp.model.RequestUser
import com.example.restoapp.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repository : UserRepository) : ViewModel() {
    val liveDataUserApi = MutableLiveData<List<GetAllUserResponseItem>>()
    fun getAllUser(){
        val response = repository.getAllUser()
        response.enqueue(object : Callback<List<GetAllUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GetAllUserResponseItem>>,
                response: Response<List<GetAllUserResponseItem>>
            ) {
                liveDataUserApi.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                //nothing
            }

        })
    }
    fun addNewUser(
        image: String,
        password: String,
        position: String,
        username: String
    ) : Boolean{
        var messageResponse = false
        val response = repository.addDataUser(
            RequestUser(image, password, position, username)
        )
        response.enqueue(object : Callback<PostNewUser> {
            override fun onResponse(call: Call<PostNewUser>, response: Response<PostNewUser>) {
                messageResponse = response.isSuccessful
            }

            override fun onFailure(call: Call<PostNewUser>, t: Throwable) {
                messageResponse = false
            }
        })
        return messageResponse
    }
    fun updateUser(
        id : String,
        image: String,
        password: String,
        position: String,
        username: String
    ) : Boolean{
        var message = false
        val response = repository.updateDataUser(
            id, RequestUser(image, password, position, username)
        )
        response.enqueue(object : Callback<List<GetAllUserResponseItem>> {
            override fun onResponse(
                call: Call<List<GetAllUserResponseItem>>,
                response: Response<List<GetAllUserResponseItem>>
            ) {
                message = response.isSuccessful
            }

            override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                message = false
            }

        })
        return message
    }
}