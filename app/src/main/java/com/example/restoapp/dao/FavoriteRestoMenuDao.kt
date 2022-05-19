package com.example.restoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.restoapp.dataclass.FavoriteRestoMenu

@Dao
interface FavoriteRestoMenuDao {
    @Insert
    suspend fun inserFavoriteMenu(favoriteRestoMenu: FavoriteRestoMenu)
    @Query("SELECT * FROM favorite_resto_menu")
    fun getFavoriteMenu() : LiveData<List<FavoriteRestoMenu>>
    @Query("DELETE FROM favorite_resto_menu WHERE id = :id")
    suspend fun deleteFavoriteMenuyId(id: Int)
}