package com.example.ex04_viewevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.ex04_viewevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindAct.root)

     /*   bindAct.chk1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
           // if(buttonView.id==bindAct.chk1.id)
            Log.d("이벤트", " 체크 상태 : $isChecked")
        })


        bindAct.chk2.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                 Log.d("이벤트", " 체크 상태 : $isChecked")
            }

        })*/
    }
}