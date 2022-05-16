package com.example.ex01_helloworld_ktn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val valData1 = "10" // val == value == final,  var variable != final
    var varData1 = "10"
    var varData2:String = "10"
    var varAnyData:Any = "10"
    val varLazy by lazy {
        Log.d(TAG, " Lazy변수를 사용하기 전 어떤 코드를 실행함."  )
        20
    }



    val TAG = "변수"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, " valData1: $valData1"  ) //문자열에 템플릿 넣기
        Log.d(TAG, " varData1: $varData1"  )
        //valData1 = "20" error
        varData1 = "20"
        Log.d(TAG, " varData1: $varData1"  )
        Log.d(TAG, " varData1: $varLazy"  )

        Log.d(TAG, """   문자열에 
            |   줄바꿈을 넣는다
        """)
        Log.d(TAG, "코틀린에서 any는 어떠한 데이터든 들어감 == Object $varAnyData")
        varAnyData = 4.5
        Log.d(TAG, "코틀린에서 any는 어떠한 데이터는 들어감 == Object $varAnyData")

        methodUnit()
        methodUnit2()
        methodUnit2("오버로딩 메소드 ")
        Log.d(TAG, "onCreate: ${methodUnit3("테스트")}")
        Log.d(TAG, "onCreate: ${methodUnit4("테스트")}")



    }


    fun methodUnit():Unit{
        Log.d(TAG, "Unit은 return이 없는 메소드 입니다.")
    }
    fun methodUnit2(){
        Log.d(TAG, "Unit을 생략해도 void")
        //return 0
    }
    fun methodUnit2(inputData:String){ //매개변수명:타입 <= var , val 사용 못함 기본적으로  val이라서 수정이 안된다고함.
        //inputData = "333" < - val이라서 수정 불가!
        Log.d(TAG, "메소드 오버로딩 $inputData}")
        //return 0
    }
    fun methodUnit3(inputData:String):Int{ //매개변수명:타입 : 리턴타입!
        Log.d(TAG, "리턴타입이 있음 $inputData")
        return 10
    }

    fun methodUnit4(inputData:String , inputData2: String="초기값 할당도 가능"):Int{ //매개변수명:타입 : 리턴타입!
        Log.d(TAG, "리턴타입이 있음 $inputData   $inputData2")
        return 10
    }
}