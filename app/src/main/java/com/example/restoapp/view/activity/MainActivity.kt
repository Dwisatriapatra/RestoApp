package com.example.restoapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restoapp.BuildConfig
import com.example.restoapp.R
import com.example.restoapp.datastore.UserLoginManager
import com.example.restoapp.networking.ApiRestoMenuServices
import com.example.restoapp.repository.RestoMenuRepository
import com.example.restoapp.view.adapter.RestoMenuAdapter
import com.example.restoapp.viewmodel.RestoMenuViewModel
import com.example.restoapp.viewmodel.RestoMenuViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var userLoginManager: UserLoginManager
    private lateinit var restoMenuViewModel: RestoMenuViewModel
    private lateinit var adapter: RestoMenuAdapter
    private val apiRestoMenuServices = ApiRestoMenuServices.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        getRestoMenuViewModel()
        getUserProfile()

        home_to_favorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getUserProfile() {
        userLoginManager = UserLoginManager(this)
        userLoginManager.username.asLiveData().observe(this){
            home_username_text.text = "Welcome, $it"
        }
        userLoginManager.image.asLiveData().observe(this){
            Glide.with(profile.context)
                .load(it)
                .error(R.drawable.ic_launcher_background)
                .into(profile)
        }
    }

    private fun getRestoMenuViewModel() {
        restoMenuViewModel = ViewModelProvider(this, RestoMenuViewModelFactory(RestoMenuRepository(apiRestoMenuServices))
        ).get(RestoMenuViewModel::class.java)

        restoMenuViewModel.liveDataRestoMenu.observe(this){
            adapter.setDataRestoMenu(it)
            adapter.notifyDataSetChanged()
        }
        restoMenuViewModel.getALlRestoMenu()
    }

    private fun initView() {
        if(BuildConfig.FLAVOR == "customer"){
            fab_tambah.isInvisible = true
        }else if(BuildConfig.FLAVOR == "seller"){
            home_to_favorite.isInvisible = true
        }

        rv_resto_menu.layoutManager = LinearLayoutManager(this)
        adapter = RestoMenuAdapter {
            val clickedMenu = Bundle()
            clickedMenu.putSerializable("MENU", it)
            val intent = Intent(this, DetailActivity::class.java).putExtras(clickedMenu)
            startActivity(intent)
        }
        rv_resto_menu.adapter = adapter

    }
}