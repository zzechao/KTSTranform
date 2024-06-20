package com.zhouz.ktstranform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView

private val handler = Handler(Looper.getMainLooper())

const val KEY = "sess-LeIcS33uHYLlyIYjIDUE9vyqhYdmsRfFNu8r3q2q"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler.post {
            findViewById<TextView>(R.id.material_hour_tv)
        }
    }
}