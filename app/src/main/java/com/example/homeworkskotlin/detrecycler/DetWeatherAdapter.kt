package com.example.homeworkskotlin.detrecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetWeatherAdapter(private val dataSrc: List<DataPair>) :
    RecyclerView.Adapter<DetWeatherItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetWeatherItem =
        DetWeatherItem.create(parent)

    override fun getItemCount(): Int = dataSrc.size

    override fun onBindViewHolder(holder: DetWeatherItem, position: Int) =
        holder.bind(dataSrc[position])
}
