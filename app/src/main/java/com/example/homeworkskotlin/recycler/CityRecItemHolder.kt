package com.example.homeworkskotlin.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.TempHelper
import com.example.homeworkskotlin.response.WeatherResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rec_item.*

class CityRecItemHolder(
    override val containerView: View,
    private val clickLambda: (WeatherResponse) -> Unit
) :

    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(wr: WeatherResponse) {

        city_name_tv.text = wr.name
        temp_tv.text = wr.main.temp.toString() + " ℃"
        val currentTemperature = wr.main.temp.toInt()
        var chosenColor = R.color.colorSecondaryText
        when {
            currentTemperature < TempHelper.CAF.maxTemp -> chosenColor = R.color.tempColdAsFuk
            currentTemperature < TempHelper.VC.maxTemp -> chosenColor = R.color.tempVeryCold
            currentTemperature < TempHelper.COLD.maxTemp -> chosenColor = R.color.tempCold
            currentTemperature < TempHelper.COOL.maxTemp -> chosenColor = R.color.tempCool
            currentTemperature < TempHelper.WARM.maxTemp -> chosenColor = R.color.tempWarm
            currentTemperature < TempHelper.HOT.maxTemp -> chosenColor = R.color.tempHot
            currentTemperature < TempHelper.VH.maxTemp -> chosenColor = R.color.tempVeryHot
            currentTemperature >= TempHelper.VH.maxTemp -> chosenColor = R.color.tempHotDog
        }
        temp_tv.setTextColor(getColor(containerView.context, chosenColor))
        itemView.setOnClickListener {
            clickLambda(wr)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (WeatherResponse) -> Unit) = CityRecItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rec_item,
                parent,
                false
            ), clickLambda
        )
    }

}
