package com.example.facebook.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.Feed
import com.example.facebook.R

class FeedAdapter(private val feedList: List<Feed>): RecyclerView.Adapter<FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FeedViewHolder(layoutInflater.inflate(R.layout.item_feed,parent, false))
    }

    override fun getItemCount(): Int = feedList.size


    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = feedList[position]
        holder.render(item)

    }
}