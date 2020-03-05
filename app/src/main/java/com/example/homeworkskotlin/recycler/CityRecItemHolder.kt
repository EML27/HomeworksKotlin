package com.example.homeworkskotlin.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.R
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
        temp_tv.text = (wr.main.temp).toString() + " ℃"
        val x = wr.main.temp.toInt()
        var chosenColor = R.color.colorSecondaryText
        when {
            x < -40 -> chosenColor = R.color.tempColdAsFuk
            x < -30 && x >= -40 -> chosenColor = R.color.tempVeryCold
            x < -10 && x >= -30 -> chosenColor = R.color.tempCold
            x < 5 && x >= -10 -> chosenColor = R.color.tempCool
            x in 5..14 -> chosenColor = R.color.tempWarm
            x in 15..24 -> chosenColor = R.color.tempHot
            x in 25..34 -> chosenColor = R.color.tempVeryHot
            x >= 35 -> chosenColor = R.color.tempHotDog
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