package com.example.testjk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testjk.databinding.ActivityKotlinBinding

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val bindAct = ActivityKotlinBinding.inflate(layoutInflater)
        setContentView(bindAct.root)


    }
}