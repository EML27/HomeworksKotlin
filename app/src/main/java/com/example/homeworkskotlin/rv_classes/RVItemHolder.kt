package com.example.homeworkskotlin.rv_classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.Track
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.track_item.*

class RVItemHolder(
    override val containerView: View,
    private val clickLambda: (Track, View) -> Unit
) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(track: Track) {
        tv_song_name.text = track.name
        tv_song_author.text = track.author
        cover_img.setImageResource(track.coverSrc)
        itemView.setOnClickListener {
            clickLambda(track, cover_img)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Track, View) -> Unit) = RVItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.track_item, parent, false
            ), clickLambda
        )
    }
}
