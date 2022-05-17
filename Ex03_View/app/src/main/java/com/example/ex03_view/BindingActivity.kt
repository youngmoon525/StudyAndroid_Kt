package com.example.ex03_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.example.ex03_view.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {
    /*Gradle에 추가해야함.
        viewBinding{
        enabled = true
        
        
        
    tools:viewBindingIgnore="true"<- 바인딩 방법 못쓰게 막기
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityBindingBinding.inflate((layoutInflater)) // 현재 액티비티 이름 + Binding
        setContentView(binding.root)
        
        binding.button1.setOnClickListener{
            Toast.makeText(applicationContext, "이것은 메세지", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_UP->{
                Log.d("모션" , "onTouchEvent X: ${event.x}  Y: $${event.y}")
            }
            MotionEvent.ACTION_DOWN->{
                Log.d("모션" , "onTouchEvent X: ${event.x}  Y: $${event.y}")
            }
            MotionEvent.ACTION_MOVE->{
                Log.d("모션" , "onTouchEvent X: ${event.x}  Y: $${event.y}")
            }
        }

        return super.onTouchEvent(event)
    }
}