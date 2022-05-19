package com.example.restoapp.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.FieldPosition

class UserLoginManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore("login-prefs")

    companion object {

        val IMAGE = preferencesKey<String>("IMAGE")
        val USERNAME = preferencesKey<String>("USERNAME")
        val PASSWORD = preferencesKey<String>("PASSWORD")
        val POSITION = preferencesKey<String>("POSITION")
        val IDUSER = preferencesKey<String>("IDUSER")
        val BOOLEAN = preferencesKey<Boolean>("BOOLEAN")

    }

    suspend fun saveDataLogin(
        id: String,
        image: String,
        password: String,
        username: String,
        position: String
    ) {
        dataStore.edit {
            it[IDUSER] = id
            it[IMAGE] = image
            it[PASSWORD] = password
            it[USERNAME] = username
            it[POSITION] = position
        }
    }

    suspend fun setBoolean(boolean: Boolean){
        dataStore.edit {
            it[BOOLEAN] = boolean
        }
    }

    suspend fun clearDataLogin(){
        dataStore.edit {
            it.clear()
        }
    }

    val IDuser : Flow<String> = dataStore.data.map {
        it[IDUSER] ?: ""
    }

    val image : Flow<String> = dataStore.data.map {
        it[IMAGE] ?: ""
    }

    val password : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }
    val username : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    val boolean : Flow<Boolean> = dataStore.data.map {
        it[BOOLEAN] ?: false
    }
    val position : Flow<String> = dataStore.data.map {
        it[POSITION] ?: ""
    }
}