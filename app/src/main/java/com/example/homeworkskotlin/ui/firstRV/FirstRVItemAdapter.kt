package com.example.homeworkskotlin.ui.firstRV

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.God
import com.example.homeworkskotlin.GodsDiffUtilCallback
import com.example.homeworkskotlin.godsList

class FirstRVItemAdapter(var dataSrc: ArrayList<God>) : RecyclerView.Adapter<FirstRVItemHolder>() {
    override fun getItemCount(): Int = dataSrc.size


    override fun onBindViewHolder(holder: FirstRVItemHolder, position: Int, payload: List<Any>) {
        if (payload.isEmpty()) {
            super.onBindViewHolder(holder, position, payload)
        } else {
            var o: Bundle = payload.get(0) as Bundle
            for (key in o.keySet()) {
                var name = ""
                var power = ""
                if (key === "key name") {

                    name = o.getString(key)

                } else if (key === "key power") {

                    power = o.getString(key)
                }
                holder.bind(God(name, power))
            }

        }
    }

    override fun onBindViewHolder(holder: FirstRVItemHolder, position: Int) =
        holder.bind(dataSrc[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstRVItemHolder =
        FirstRVItemHolder.create(parent) {
            godsList.deleteGod(it)
            updateRV()
        }

    fun updateRV() {
        val gduc =
            GodsDiffUtilCallback(dataSrc, godsList.getData())
        val res = DiffUtil.calculateDiff(gduc)

        dataSrc.clear()
        dataSrc.addAll(godsList.getData())
        res.dispatchUpdatesTo(this)
    }
}
