package com.example.doodleandroid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmarc.R
import com.example.mvvmarc.model.Meme

class RecyclerAdaptor(var list: ArrayList<Meme>, ):RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent:ViewGroup,ViewType:Int):ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.widget_tile,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder:ViewHolder, position:Int) {
        holder.text.text=list[position].name
       Glide.with(holder.img).load(list[position].url).into(holder.img)
    }
    override fun getItemCount():Int {
        return list.size
    }

    class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        var text=view.findViewById<TextView>(R.id.txt_title)
        var img=view.findViewById<ImageView>(R.id.circle_img)


    }
}
