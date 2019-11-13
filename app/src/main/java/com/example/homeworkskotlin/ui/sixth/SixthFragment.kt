package com.example.homeworkskotlin.ui.sixth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkskotlin.R
import kotlinx.android.synthetic.main.fragment_sixth.*


class SixthFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sixth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.getArguments()
        var name = bundle?.getString("name") ?: "default name"
        var surname = bundle?.getString("surname") ?: "default surname"
        var gender = bundle?.getString("gender") ?: "default gender"
        tv_the_truth.text = "Lol, $name $surname, u r not $gender, u gay"
    }


}
