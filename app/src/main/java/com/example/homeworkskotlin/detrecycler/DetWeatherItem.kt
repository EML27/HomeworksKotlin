package com.example.homeworkskotlin.detrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.det_rec_item.*

class DetWeatherItem(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(data: DataPair) {
        detailed_rec_parameter_name_tv.text = data.name
        detailed_rec_parameter_value_tv.text = data.value
    }

    companion object {
        fun create(parent: ViewGroup) = DetWeatherItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.det_rec_item,
                parent,
                false
            )
        )
    }
}