package com.example.facebook.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.facebook.Feed
import com.example.facebook.R

class FeedViewHolder( val view: View): ViewHolder(view) {


    fun render(feed: Feed){
        val imagenUsuario = view.findViewById<ImageView>(R.id.imagen_usuario)
        val usuario = view.findViewById<TextView>(R.id.id_usuario)
        val fecha = view.findViewById<TextView>(R.id.fecha_feed)

        val descipcion = view.findViewById<TextView>(R.id.id_descripcion)
        val imagen_feed = view.findViewById<ImageView>(R.id.img_Feed)

        val reacciones = view.findViewById<TextView>(R.id.id_reaccions)
        val comentarios = view.findViewById<TextView>(R.id.tx_comentarios)

        Glide.with(imagenUsuario.context).load(feed.imagen_usuario).into(imagenUsuario)
        usuario.text = feed.usuario
        fecha.text = feed.fecha

        descipcion.text = feed.decripcion_feed
        Glide.with(imagen_feed.context).load(feed.imagen_feed).into(imagen_feed)

        reacciones.text = feed.reacciones
        if (feed.comentarios != 0 )
            comentarios.text =  feed.comentarios.toString() + (comentarios.text.toString() + " comentarios ")
        if (feed.comentarios != 0 && feed.compartidos != 0)
            comentarios.text = comentarios.text.toString() + "• "
        if (feed.compartidos != 0)
            comentarios.text = comentarios.text.toString() + feed.compartidos.toString() +  " veces compartido"

    }
}




