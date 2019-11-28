package com.example.homeworkskotlin.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkskotlin.Creature
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.creaturesList
import com.example.homeworkskotlin.ui.secondRV.SecondRVAdapter
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_second.adapter = SecondRVAdapter(ArrayList<Creature>().apply { creaturesList.getData() })
    }
}
