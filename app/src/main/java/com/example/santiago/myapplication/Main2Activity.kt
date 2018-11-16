package com.example.santiago.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
import com.example.santiago.myapplication.MainActivity.Companion.TASK_DATA
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        submitButton.setOnClickListener {

            val text : String = inputText.text.toString()
            if (text.isNullOrEmpty()) {
                inputText.error="Por favor rellene el campo"
            }else {
                var data = Task(HashUtils.sha1(System.currentTimeMillis().toString()), text, 3, true, true)
                setResult(Activity.RESULT_OK, Intent().putExtra(TASK_DATA, data))
                finish()
            }

        }
    }
}

