package com.example.restoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restoapp.repository.UserRepository

class UserViewModelFactory constructor(private val repository : UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("View model not found")
        }
    }
}