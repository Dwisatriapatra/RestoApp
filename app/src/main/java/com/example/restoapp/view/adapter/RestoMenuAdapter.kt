package com.example.restoapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restoapp.R
import com.example.restoapp.model.GetAllRestoMenuResponseItem
import kotlinx.android.synthetic.main.item_resto_adapter.view.*

class RestoMenuAdapter(private val onClick : (GetAllRestoMenuResponseItem) -> Unit) :
RecyclerView.Adapter<RestoMenuAdapter.ViewHolder>() {
    private var listMenu : List<GetAllRestoMenuResponseItem>? = null
    fun setDataRestoMenu(list : List<GetAllRestoMenuResponseItem>){
        this.listMenu = list
        notifyDataSetChanged()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoMenuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestoMenuAdapter.ViewHolder, position: Int) {
        with(holder.itemView){
            with(listMenu!![position]){
                resto_menu_nama.text = nama
                resto_menu_harga.text = harga
                Glide.with(resto_menu_image.context)
                    .load(image)
                    .error(R.drawable.ic_launcher_background)
                    .into(resto_menu_image)

            }
        }
        holder.itemView.button_see_detail.setOnClickListener {
            onClick(listMenu!![position])
        }
    }

    override fun getItemCount(): Int {
        return if(listMenu.isNullOrEmpty()){
            0
        }else{
            listMenu!!.size
        }
    }

}