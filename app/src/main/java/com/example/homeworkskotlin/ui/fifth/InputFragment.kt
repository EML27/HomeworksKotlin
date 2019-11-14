package com.example.homeworkskotlin.ui.fifth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkskotlin.R
import com.example.homeworkskotlin.ui.sixth.TruthFragment
import kotlinx.android.synthetic.main.content_navigation.*
import kotlinx.android.synthetic.main.fragment_fifth.*


class InputFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.title = "Input"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifth, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        get_the_truth_btn.setOnClickListener {

            var bundle = Bundle()
            bundle.putString("name", et_name.text.toString())
            bundle.putString("surname", et_surname.text.toString())
            bundle.putString("gender", et_gender.text.toString())
            var nextFragment = TruthFragment()
            nextFragment.arguments = bundle

            fragmentManager?.beginTransaction()
                ?.replace(nav_host_fragment.id, nextFragment)
                //?.addToBackStack("")
                ?.commit()
        }
    }


}
