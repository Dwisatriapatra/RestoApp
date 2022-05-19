package com.example.restoapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.restoapp.R
import com.example.restoapp.networking.ApiUserServices
import com.example.restoapp.repository.UserRepository
import com.example.restoapp.viewmodel.UserViewModel
import com.example.restoapp.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var userViewModel : UserViewModel
    private val apiUserServices = ApiUserServices.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button_register.setOnClickListener {
            tambahUser()
        }
    }

    private fun tambahUser() {
        val username = register_input_nama.text.toString()
        val password = register_input_password.text.toString()
        val posisi = register_input_posisi.text.toString()
        val image = "http://placeimg.com/640/480/city"

        if (username.isNotEmpty() && password.isNotEmpty() && posisi.isNotEmpty()
        ) {
            postUser(image, password, posisi, username)
        } else {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun postUser(
        image : String,
        password: String,
        position: String,
        username: String
    ) {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository(apiUserServices))
        ).get(UserViewModel::class.java)
        userViewModel.addNewUser(image, password, position, username)

        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
