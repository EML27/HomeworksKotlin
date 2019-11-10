package com.example.homeworkskotlin.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.ui.fifth.FifthFragment
import kotlinx.android.synthetic.main.content_navigation.*
import kotlinx.android.synthetic.main.fragment_send.*

class SendFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_send, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        open_new_frag_btn.setOnClickListener {
            val tr = fragmentManager?.beginTransaction()
            tr?.replace(nav_host_fragment.id, FifthFragment())
            tr?.addToBackStack(null)
            tr?.commit()
        }
    }
}