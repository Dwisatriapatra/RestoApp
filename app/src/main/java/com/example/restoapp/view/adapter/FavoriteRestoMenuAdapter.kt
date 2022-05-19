package com.example.restoapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restoapp.R
import com.example.restoapp.dataclass.FavoriteRestoMenu
import kotlinx.android.synthetic.main.item_resto_adapter.view.*

class FavoriteRestoMenuAdapter(private val onClick : (FavoriteRestoMenu) -> Unit) :
RecyclerView.Adapter<FavoriteRestoMenuAdapter.ViewHolder>(){
    private var listFavoriteMenu : List<FavoriteRestoMenu>? = null
    fun setDataFavoriteRestoMenu(list : List<FavoriteRestoMenu>){
        this.listFavoriteMenu = list
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            with(listFavoriteMenu!![position]){
                resto_menu_nama.text = nama
                resto_menu_harga.text = harga
                Glide.with(resto_menu_image.context)
                    .load(image)
                    .error(R.drawable.ic_launcher_background)
                    .into(resto_menu_image)

            }
        }
        holder.itemView.button_see_detail.setOnClickListener {
            onClick(listFavoriteMenu!![position])
        }
    }

    override fun getItemCount(): Int {
        return if(listFavoriteMenu.isNullOrEmpty()){
            0
        }else{
            listFavoriteMenu!!.size
        }
    }

}