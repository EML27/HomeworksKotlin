package com.example.homeworkskotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.homeworkskotlin.ui.firstRV.FirstRVItemAdapter
import kotlinx.android.synthetic.main.fragment_my_dialog.*
import java.lang.Integer.parseInt

class DialogFrag(val adapter: FirstRVItemAdapter) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_god.setOnClickListener {
            if (et_name.text.isEmpty() || et_power.text.isEmpty()) {
                Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show()
            } else {
                var name = et_name.text.toString()
                var power = et_power.text.toString()
                if (et_number.text.isEmpty() || parseInt(et_number.text.toString()) > godsList.size()) {
                    godsList.addGod(name, power)
                } else {
                    godsList.addGod(name, power, parseInt(et_number.text.toString()))
                }
                adapter.updateRV()
                dismiss()
            }
        }


    }

}
