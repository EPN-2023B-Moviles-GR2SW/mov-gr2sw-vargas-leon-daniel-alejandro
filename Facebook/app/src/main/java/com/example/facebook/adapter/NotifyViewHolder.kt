package com.example.facebook.adapter

import android.text.Layout
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebook.Feed
import com.example.facebook.Notify
import com.example.facebook.R

class NotifyViewHolder( val view: View): RecyclerView.ViewHolder(view) {
    fun render(notifiacion: Notify){

        val imagen_usuario = view.findViewById<ImageView>(R.id.imagen_notify)
        val descipcion = view.findViewById<TextView>(R.id.descipcion_notify)
        val fecha = view.findViewById<TextView>(R.id.fecha_notify)
        descipcion.text = notifiacion.descipcion_notificacion
        fecha.text = notifiacion.fecha
        Glide.with(imagen_usuario.context).load(notifiacion.imagen_notificacion).into(imagen_usuario)
        if (notifiacion.visto){

        }else
            view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.notificacion))


    }
}