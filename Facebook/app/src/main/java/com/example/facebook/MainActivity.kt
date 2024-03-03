package com.example.facebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.adapter.FeedAdapter
import com.example.facebook.adapter.NotifyAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var posicionLibro= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHomeRecycleView()

        val botonNotificacion = findViewById<Button>(R.id.btn_notificacion)
        botonNotificacion.setOnClickListener {
            initNotifyRecycleView()
        }
        val botonHome = findViewById<Button>(R.id.btn_Home)
        botonHome.setOnClickListener {
            initHomeRecycleView()
        }


    }

    private fun initHomeRecycleView() {

        val recycleView = findViewById<RecyclerView>(R.id.recyclerFeed)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = FeedAdapter(feedProvider.feedList)
    }

    private fun initNotifyRecycleView(){
        val recycleView = findViewById<RecyclerView>(R.id.recyclerFeed)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = NotifyAdapter(feedProvider.notifyList)
    }





    }