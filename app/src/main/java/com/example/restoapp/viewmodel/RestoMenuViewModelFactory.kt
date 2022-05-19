package com.example.restoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restoapp.repository.RestoMenuRepository

class RestoMenuViewModelFactory constructor(private val repository : RestoMenuRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(RestoMenuViewModel::class.java)){
            RestoMenuViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("View model not found")
        }
    }
}