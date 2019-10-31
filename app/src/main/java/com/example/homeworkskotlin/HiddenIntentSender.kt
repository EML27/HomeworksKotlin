package com.example.homeworkskotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hidden_intent_sender.*
private const val SEND_REQUEST =1
class HiddenIntentSender : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hidden_intent_sender)
        btn_send_intent.setOnClickListener { val sendIntent: Intent= Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"Chto takoe pomelo blin???")
            type="text/plain"
        }
            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivityForResult(shareIntent,SEND_REQUEST) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SEND_REQUEST && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()

        }
    }
}
