package com.example.creativemindstask.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.creativemindstask.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        designTaskBtn.setOnClickListener {
            startActivity(Intent(this, DesignTaskActivity::class.java))
        }

        developingTaskBtn.setOnClickListener {
            startActivity(Intent(this, DevelopingActivity::class.java))
        }

    }
}