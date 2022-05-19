package com.example.restoapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restoapp.dao.FavoriteRestoMenuDao
import com.example.restoapp.dataclass.FavoriteRestoMenu

@Database(entities = [FavoriteRestoMenu::class], version = 1, exportSchema = false)

abstract class FavoriteRestoMenuDatabase : RoomDatabase(){
    abstract fun favoriteRestoMenuDao() : FavoriteRestoMenuDao

    companion object{
        private var INSTANCE : FavoriteRestoMenuDatabase? = null

        fun getInstance(context: Context) : FavoriteRestoMenuDatabase?{
            if(INSTANCE == null){
                synchronized(FavoriteRestoMenuDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteRestoMenuDatabase::class.java, "FavoriteRestoMenu.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}