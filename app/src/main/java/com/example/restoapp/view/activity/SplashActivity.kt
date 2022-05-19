package com.example.restoapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.asLiveData
import com.example.restoapp.R
import com.example.restoapp.datastore.UserLoginManager

class SplashActivity : AppCompatActivity() {

    private lateinit var userLoginManager: UserLoginManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userLoginManager = UserLoginManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            userLoginManager.boolean.asLiveData().observe(this){
                if(it == true){
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }, 3000)
    }
}