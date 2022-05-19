package com.example.restoapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restoapp.R
import com.example.restoapp.datastore.UserLoginManager
import com.example.restoapp.repository.RestoMenuFavoriteRepository
import com.example.restoapp.roomdatabase.FavoriteRestoMenuDatabase
import com.example.restoapp.view.adapter.FavoriteRestoMenuAdapter
import com.example.restoapp.viewmodel.RestoMenuFavoriteViewModel
import com.example.restoapp.viewmodel.RestoMenuFavoriteViewModelFactory
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoriteRestoMenuDatabase: FavoriteRestoMenuDatabase
    private lateinit var repository: RestoMenuFavoriteRepository
    private lateinit var factory: RestoMenuFavoriteViewModelFactory
    private lateinit var viewModel: RestoMenuFavoriteViewModel
    private lateinit var username : String
    private lateinit var userLoginManager: UserLoginManager
    private lateinit var adapter: FavoriteRestoMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteRestoMenuDatabase = FavoriteRestoMenuDatabase.getInstance(this)!!
        repository = RestoMenuFavoriteRepository(favoriteRestoMenuDatabase)
        factory = RestoMenuFavoriteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RestoMenuFavoriteViewModel::class.java]

        userLoginManager = UserLoginManager(this)
        userLoginManager.username.asLiveData().observe(this){
            username = it.toString()
        }

        initRecyclerView()
        getFavoriteRestoMenuData()
    }

    private fun initRecyclerView() {
        rv_favorite_resto_menu.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteRestoMenuAdapter{
            val clickedMenu = Bundle()
            clickedMenu.putParcelable("FAVORITEMENU", it)
            val intent = Intent(this, DetailActivity::class.java).putExtras(clickedMenu)
            startActivity(intent)
        }
        rv_favorite_resto_menu.adapter = adapter
    }

    private fun getFavoriteRestoMenuData() {
        viewModel.getFavoriteMenu().observe(this){
            if(it.isNotEmpty()){
                adapter.setDataFavoriteRestoMenu(it)
                adapter.notifyDataSetChanged()
            }else{
            }
        }
    }
}