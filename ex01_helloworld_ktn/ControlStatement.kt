package com.example.ex01_helloworld_ktn

import android.util.Log

class ControlStatement {
//조건문 부분은 비슷하지만 바로 변수에 대입해서 사용이 가능하다.
    val TAG = "제어문"
    fun ifelse(){
        var data = 100
        val result = if( data >= 100 ){
            Log.d(TAG, "ifelse: ")
            "참"
        }else{
            "거짓"
        }
        Log.d(TAG, " $result")

        var result2 = if(data >= 100){
            10
        }else{
            20
        }
        Log.d(TAG, "$result2")
        result2 = 30
        Log.d(TAG, "$result2")
    }

    fun whenif(){
        //코틀린에서는 when이라는 조건이 있음
        var data =  1
        when(data){
            in 1..100 -> Log.d(TAG, "1~100사이의 수인것")
            1 -> Log.d(TAG, " 해당하는 조건을 만족함")
            2 -> Log.d(TAG, " 해당하는 조건을 만족함")
            else -> Log.d(TAG, "모든 조건을 만족하지 못함")
        }
    }

    fun whenif2(){
        //코틀린에서는 when이라는 조건이 있음
        var data = "스트링값을넣음"
        when(data){
            is String -> Log.d(TAG, " 스트링 타입인지를 비교할수있음")
            else -> Log.d(TAG, "모든 조건을 만족하지 못함")
        }

        //if else 문과 마찬가지로 담기도 바로가능..
        data = when {
            data is String -> "스트링임"
            else -> "스트링 아님" //<-else처리를 반드시 해주자
        }
        Log.d(TAG, "$data")
    }

    fun forState(){
        for(i in 1 .. 100){
        //<- 1에서부터 100까지 반복하는 반복문
        }

       for(i in 100 downTo 1){

       }
        //<- 100에서부터 1까지 감소하는 반복문
    }
}