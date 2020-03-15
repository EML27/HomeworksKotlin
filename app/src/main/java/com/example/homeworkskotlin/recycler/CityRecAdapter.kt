package com.example.homeworkskotlin.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.response.WeatherResponse

class CityRecAdapter(
    private val dataSrc: List<WeatherResponse>,
    private val clickLambda: (WeatherResponse) -> Unit
) :
    RecyclerView.Adapter<CityRecItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityRecItemHolder =
        CityRecItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSrc.size

    override fun onBindViewHolder(holder: CityRecItemHolder, position: Int) =
        holder.bind(dataSrc[position])
}
