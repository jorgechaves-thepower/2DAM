package com.example.appmultimedia

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoView = findViewById<VideoView>(R.id.videoView)

        val videoId = intent.getIntExtra("video",0)

        val uri = Uri.parse("android.resource://$packageName/$videoId")
        videoView.setVideoURI(uri)

        videoView.start()
    }
}