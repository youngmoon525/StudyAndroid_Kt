package com.example.ex03_view

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*================코드로 추가하기
        val tempTextView = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = """ 추가 된
                | 텍스트 뷰"""
        }
        val tempLayout = LinearLayout(this).apply {

        }
        tempLayout.addView(tempTextView , WRAP_CONTENT , WRAP_CONTENT)
        setContentView(tempLayout)
        ===============*/
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1); // <제네릭> 으로 타입을 명시해줘도 되고 생략 해도 된다.
        button1.setOnClickListener{
            Toast.makeText( this@MainActivity,"토스트 띄우기", Toast.LENGTH_SHORT).show()
            Toast.makeText( applicationContext,"토스트 띄우기", Toast.LENGTH_SHORT).show()
        }
    }
}