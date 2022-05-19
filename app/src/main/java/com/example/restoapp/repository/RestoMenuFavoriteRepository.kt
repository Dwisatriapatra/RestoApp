package com.example.restoapp.repository

import androidx.lifecycle.LiveData
import com.example.restoapp.dataclass.FavoriteRestoMenu
import com.example.restoapp.roomdatabase.FavoriteRestoMenuDatabase

class RestoMenuFavoriteRepository(private val favoriteRestoMenuDatabase : FavoriteRestoMenuDatabase) {
    suspend fun insertFavoriteMenu(favoriteRestoMenu: FavoriteRestoMenu){
        favoriteRestoMenuDatabase.favoriteRestoMenuDao().inserFavoriteMenu(favoriteRestoMenu)
    }
    fun getFavoriteMenu() : LiveData<List<FavoriteRestoMenu>> {
        return favoriteRestoMenuDatabase.favoriteRestoMenuDao().getFavoriteMenu()
    }
    suspend fun deleteFavoriteMenuById(id: Int) = favoriteRestoMenuDatabase.favoriteRestoMenuDao().deleteFavoriteMenuyId(id)
}

