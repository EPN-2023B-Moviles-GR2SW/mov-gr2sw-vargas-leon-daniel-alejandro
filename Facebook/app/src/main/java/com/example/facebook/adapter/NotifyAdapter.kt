package com.example.facebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.Feed
import com.example.facebook.Notify
import com.example.facebook.R

class NotifyAdapter(private val notifylist:List<Notify>): RecyclerView.Adapter<NotifyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotifyViewHolder(layoutInflater.inflate(R.layout.notify_item,parent, false))
    }

    override fun getItemCount(): Int = notifylist.size


    override fun onBindViewHolder(holder: NotifyViewHolder, position: Int) {
        val item = notifylist[position]
        holder.render(item)

    }
}