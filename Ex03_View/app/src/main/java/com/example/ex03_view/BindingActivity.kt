package com.example.ex03_view

import android.content.Intent
import android.net.Uri
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
        binding.button2.setOnClickListener{

            if(binding.edtId.text.toString().equals("admin")
                && binding.edtId.text.toString().equals("admin")){
                Log.d("로그인 성공" , "onCreate: ")
            }
        }
        binding.btnNaver.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
            startActivity(intent);
        }
        binding.btnNew.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
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