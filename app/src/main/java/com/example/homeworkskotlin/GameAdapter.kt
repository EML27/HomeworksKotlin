package com.example.homeworkskotlin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val dataSrc: List<Game>,private val clickLambda:(Game)->Unit):RecyclerView.Adapter<GameItemHolder>() {
    override fun getItemCount(): Int {
        return dataSrc.size
    }

    override fun onBindViewHolder(holder: GameItemHolder, position: Int) {
        holder.bind(dataSrc[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemHolder {
        return GameItemHolder.create(parent,clickLambda)
    }
}
