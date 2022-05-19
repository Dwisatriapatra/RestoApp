package com.example.restoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.restoapp.dataclass.FavoriteRestoMenu
import com.example.restoapp.repository.RestoMenuFavoriteRepository

class RestoMenuFavoriteViewModel(
    private val repository : RestoMenuFavoriteRepository
) : ViewModel(){
    suspend fun insertFavoriteMenu(favoriteRestoMenu: FavoriteRestoMenu){
        repository.insertFavoriteMenu(favoriteRestoMenu)
    }
    fun getFavoriteMenu() = repository.getFavoriteMenu()

    suspend fun deleteFavoriteMenuyId(id: Int) = repository.deleteFavoriteMenuById(id)
}