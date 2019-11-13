package com.example.homeworkskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.game_item.*

class GameItemHolder(
    override val containerView: View,
    private val clickLambda: (Game) -> Unit
) :

    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(game: Game) {

        tv_name.text = game.name
        tv_type.text = game.type
        img_game.setImageResource(game.picSrcId)
        itemView.setOnClickListener {
            clickLambda(game)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Game) -> Unit) = GameItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.game_item,
                parent,
                false
            ), clickLambda
        )
    }

}
