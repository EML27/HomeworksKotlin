package com.example.homeworkskotlin.ui.secondRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.homeworkskotlin.Creature
import com.example.homeworkskotlin.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pager.*
import kotlinx.android.synthetic.main.second_rv_item.*

class SecondRVHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(creature: Creature) {

        profile_image.setImageResource(creature.avatarPic)
        profile_name.text = creature.name
        tv_description.text = creature.description
        vp.adapter = VPAdapter(containerView.context, creature.resources)

    }

    private inner class VPAdapter(var mContext: Context, var picsList: List<Int>) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = picsList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            //return super.instantiateItem(container, position)
            val li = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = li.inflate(R.layout.pager, container, false)
            img.setImageResource(picsList[position])
            container.addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }
    }

    companion object {
        fun create(parent: ViewGroup) = SecondRVHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.second_rv_item,
                parent,
                false
            )
        )
    }
}
