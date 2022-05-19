package com.example.restoapp.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restoapp.R
import com.example.restoapp.datastore.UserLoginManager
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private lateinit var userLoginManager: UserLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profile_button_logout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        userLoginManager = UserLoginManager(this)
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah anda yakin ingin logout?")
            .setNegativeButton("TIDAK"){ dialogInterface : DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .setPositiveButton("YA"){ _: DialogInterface, _: Int ->
                GlobalScope.launch {
                    userLoginManager.clearDataLogin()
                }
                startActivity(Intent(this, SplashActivity::class.java))
            }.show()
    }
}