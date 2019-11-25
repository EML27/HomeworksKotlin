package com.example.homeworkskotlin.ui.firstRV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.God
import com.example.homeworkskotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.first_rv_item.*


class FirstRVItemHolder(override val containerView: View, private val clickLambda: (God) -> Unit) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(god: God) {

        tv_name.text = god.name
        tv_description.text = god.power

        delete_btn.setOnClickListener {
            clickLambda(god)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (God) -> Unit) = FirstRVItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.first_rv_item,
                parent,
                false
            ), clickLambda
        )
    }
}
