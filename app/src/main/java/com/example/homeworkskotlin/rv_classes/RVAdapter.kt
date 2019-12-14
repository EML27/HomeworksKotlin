package com.example.homeworkskotlin.rv_classes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.Track

class RVAdapter(private val dataSrc: List<Track>, private val clickLamda: (Track, View) -> Unit) :
    RecyclerView.Adapter<RVItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVItemHolder =
        RVItemHolder.create(parent, clickLamda)

    override fun getItemCount(): Int = dataSrc.size

    override fun onBindViewHolder(holder: RVItemHolder, position: Int) =
        holder.bind(dataSrc[position])
}
