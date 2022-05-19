package com.example.restoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restoapp.model.GetAllRestoMenuResponseItem
import com.example.restoapp.repository.RestoMenuRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestoMenuViewModel(private val repository : RestoMenuRepository) : ViewModel() {
    val liveDataRestoMenu = MutableLiveData<List<GetAllRestoMenuResponseItem>>()
    fun getALlRestoMenu(){
        val response = repository.getALlRestoMenu()
        response.enqueue(object : Callback<List<GetAllRestoMenuResponseItem>>{
            override fun onResponse(
                call: Call<List<GetAllRestoMenuResponseItem>>,
                response: Response<List<GetAllRestoMenuResponseItem>>
            ) {
                liveDataRestoMenu.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GetAllRestoMenuResponseItem>>, t: Throwable) {
                //nothing
            }

        })
    }
}