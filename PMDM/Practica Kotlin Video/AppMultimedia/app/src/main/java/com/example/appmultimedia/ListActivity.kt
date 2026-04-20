package com.example.appmultimedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recycler = findViewById<RecyclerView>(R.id.recyclerVideos)
        recycler.layoutManager = LinearLayoutManager(this)

        val lista = listOf(
            Video("Video 1", R.drawable.ic_launcher_background, R.raw.video1),
            Video("Video 2", R.drawable.ic_launcher_background, R.raw.video2)
        )

        val adapter = VideoAdapter(lista) { video ->
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("video", video.video)
            startActivity(intent)
        }

        recycler.adapter = adapter

    }
}