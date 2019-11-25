package com.example.homeworkskotlin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkskotlin.DialogFrag
import com.example.homeworkskotlin.God
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.godsList
import com.example.homeworkskotlin.ui.firstRV.FirstRVItemAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_first.adapter = FirstRVItemAdapter(ArrayList<God>().apply { addAll(godsList.getData()) })

        fab_add_god.setOnClickListener {

            val fragmentTransaction = fragmentManager?.beginTransaction()
            val prev = fragmentManager?.findFragmentByTag("dialog")
            if (prev != null) {
                fragmentTransaction?.remove(prev)
            }
            fragmentTransaction?.addToBackStack(null)
            val dialogFragment = DialogFrag(rv_first.adapter as FirstRVItemAdapter)

            dialogFragment.show(fragmentTransaction ?: throw IllegalArgumentException(), "dialog")

        }
    }
}
