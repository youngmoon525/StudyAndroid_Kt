package com.example.ex02_lamda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG = "람다복습"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ${oldMethod(100)}")

        val some: (Int) -> Unit = { Log.d(TAG, " 람다")}
        some(10)

        val some2 = {no1: Int, no2: Int ->
            Log.d(TAG, " 람다")
            no1 * no2
        }
        println("result : ${some2(10, 20)}")
    }

    fun oldMethod(data:Int){
        Log.d(TAG, "oldMethod: ")
    }



}