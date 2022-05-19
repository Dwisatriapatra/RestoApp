package com.example.restoapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restoapp.BuildConfig
import com.example.restoapp.R
import com.example.restoapp.datastore.UserLoginManager
import com.example.restoapp.model.GetAllUserResponseItem
import com.example.restoapp.networking.ApiUserServices
import com.example.restoapp.repository.UserRepository
import com.example.restoapp.viewmodel.UserViewModel
import com.example.restoapp.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userLoginManager: UserLoginManager
    private val apiUserServices = ApiUserServices.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            initUserViewModel()
        }
        login_registering_account.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun initUserViewModel() {
        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository(apiUserServices))
        ).get(UserViewModel::class.java)
        userViewModel.liveDataUserApi.observe(this){
            if(it.isNotEmpty()){
                loginAuth(it)
            }
        }
        userViewModel.getAllUser()
    }

    private fun loginAuth(list: List<GetAllUserResponseItem>?) {
        userLoginManager = UserLoginManager(this)

        val inputanUsername = login_input_username.text.toString()
        val inputanPassword = login_input_password.text.toString()
        if(BuildConfig.FLAVOR == "customer"){
            if(inputanUsername.isNotEmpty() && inputanPassword.isNotEmpty()){
                for(i in list!!.indices){
                    if(inputanUsername == list[i].username
                        && inputanPassword == list[i].password
                        && list[i].position == "customer"
                    ){
                        Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()
                        GlobalScope.launch {
                            userLoginManager.setBoolean(true)
                            userLoginManager.saveDataLogin(
                                list[i].id,
                                list[i].image,
                                list[i].password,
                                list[i].username,
                                list[i].position
                            )
                        }
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }else{
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }else if(BuildConfig.FLAVOR == "seller"){
            if(inputanUsername.isNotEmpty() && inputanPassword.isNotEmpty()){
                for(i in list!!.indices){
                    if(inputanUsername == list[i].username
                        && inputanPassword == list[i].password
                        && list[i].position == "seller"
                    ){
                        Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()
                        GlobalScope.launch {
                            userLoginManager.setBoolean(true)
                            userLoginManager.saveDataLogin(
                                list[i].id,
                                list[i].image,
                                list[i].password,
                                list[i].username,
                                list[i].position
                            )
                        }
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }else{
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}