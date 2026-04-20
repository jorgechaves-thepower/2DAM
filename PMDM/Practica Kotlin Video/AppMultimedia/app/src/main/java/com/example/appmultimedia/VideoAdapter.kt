package com.example.appmultimedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(
    private val lista: List<Video>,
    private val onClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView = view.findViewById(R.id.txtTitulo)
        val imagen: ImageView = view.findViewById(R.id.imgVideo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = lista[position]

        holder.titulo.text = video.titulo
        holder.imagen.setImageResource(video.imagen)

        holder.itemView.setOnClickListener {
            onClick(video)
        }
    }
}