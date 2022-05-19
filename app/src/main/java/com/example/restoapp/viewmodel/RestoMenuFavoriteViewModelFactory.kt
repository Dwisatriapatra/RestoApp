package com.example.restoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restoapp.repository.RestoMenuFavoriteRepository

class RestoMenuFavoriteViewModelFactory(
    private val repository: RestoMenuFavoriteRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(RestoMenuFavoriteRepository::class.java)
            return constructor.newInstance(repository)
        }catch (e : Exception){

        }
        return super.create(modelClass)
    }
}