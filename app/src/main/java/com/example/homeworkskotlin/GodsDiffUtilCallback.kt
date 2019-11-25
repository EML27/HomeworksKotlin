package com.example.homeworkskotlin

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class GodsDiffUtilCallback(val oldList: List<God>, val newList: List<God>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var old = oldList[oldItemPosition]
        var new = newList[newItemPosition]
        return old.name == new.name
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var old = oldList[oldItemPosition]
        var new = newList[newItemPosition]
        return old.power == new.power
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //return super.getChangePayload(oldItemPosition, newItemPosition)
        var oldItem = oldList[oldItemPosition]
        var newItem = newList[newItemPosition]
        var diffBundle = Bundle()
        if (oldItem.name != newItem.name) {
            diffBundle.putString("key name", newItem.name)

        }
        if (oldItem.power != newItem.power) {
            diffBundle.putString("key power", newItem.power)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}
