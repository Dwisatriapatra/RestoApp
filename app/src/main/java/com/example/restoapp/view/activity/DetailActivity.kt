package com.example.restoapp.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.restoapp.BuildConfig
import com.example.restoapp.R
import com.example.restoapp.dataclass.FavoriteRestoMenu
import com.example.restoapp.datastore.UserLoginManager
import com.example.restoapp.model.GetAllRestoMenuResponseItem
import com.example.restoapp.repository.RestoMenuFavoriteRepository
import com.example.restoapp.roomdatabase.FavoriteRestoMenuDatabase
import com.example.restoapp.viewmodel.RestoMenuFavoriteViewModel
import com.example.restoapp.viewmodel.RestoMenuFavoriteViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: RestoMenuFavoriteViewModel
    private lateinit var database: FavoriteRestoMenuDatabase
    private lateinit var factory: RestoMenuFavoriteViewModelFactory
    private lateinit var userLoginManager: UserLoginManager
    private lateinit var repository : RestoMenuFavoriteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        database = FavoriteRestoMenuDatabase.getInstance(this)!!
        repository = RestoMenuFavoriteRepository(database)
        factory = RestoMenuFavoriteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RestoMenuFavoriteViewModel::class.java]

        if(BuildConfig.FLAVOR == "seller"){
            detail_add_to_favorite.isInvisible = true
        }
        initView()

    }

    private fun initView() {
        val data = intent.extras!!.getSerializable("MENU") as GetAllRestoMenuResponseItem
        detail_nama_makanan.text = data.nama
        detail_deskripsi.text = data.deskripsi
        detail_harga.text = data.harga
        Glide.with(detail_resto_menu_image.context)
            .load(data.image)
            .error(R.drawable.ic_launcher_background)
            .into(detail_resto_menu_image)
        detail_add_to_favorite.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Tambah ke favorit")
                .setMessage("Yalin ingin menambah menu ini ke favorit?")
                .setNegativeButton("TIDAK"){ dialogInterface : DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }
                .setPositiveButton("YA"){ _: DialogInterface, _ : Int ->
                    insertNewFavoriteMenu(FavoriteRestoMenu(
                        null,
                        data.deskripsi,
                        data.harga,
                        data.image,
                        data.nama
                    ))
                }.show()
        }
    }

    private fun insertNewFavoriteMenu(favoriteRestoMenu: FavoriteRestoMenu) {

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.insertFavoriteMenu(favoriteRestoMenu).also {
                Toast.makeText(this@DetailActivity, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}