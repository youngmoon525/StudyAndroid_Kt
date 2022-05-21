package com.example.ex03_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.ex03_view.databinding.ActivityEtcViewBinding

class EtcViewActivity : AppCompatActivity() ,CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEtcViewBinding.inflate(layoutInflater)

        //  val binding = ActivityBindingBinding.inflate((layoutInflater)) // 현재 액티비티 이름 + Binding
        setContentView(binding.root)


        binding.chkCheck.setOnCheckedChangeListener { chk_btn, isChecked ->
            Log.d("버튼이 체크되었는지?", "onCreate: ${isChecked}")
            // kotlin
            //        binding.chkCheck.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            //            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            //            }
            //
            //        });
        }

        binding.chkCheck2.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("SAM기법입니다.", "$isChecked       $buttonView.text")
    }
}