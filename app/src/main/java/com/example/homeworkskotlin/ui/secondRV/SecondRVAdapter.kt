package com.example.homeworkskotlin.ui.secondRV

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.Creature

class SecondRVAdapter(var dataSrc: ArrayList<Creature>) : RecyclerView.Adapter<SecondRVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondRVHolder =
        SecondRVHolder.create(parent)

    override fun getItemCount(): Int =
        dataSrc.size

    override fun onBindViewHolder(holder: SecondRVHolder, position: Int) =
        holder.bind(dataSrc[position])

}
