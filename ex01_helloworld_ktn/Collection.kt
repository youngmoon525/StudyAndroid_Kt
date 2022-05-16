package com.example.ex01_helloworld_ktn

import android.util.Log

class Collection {

    val TAG = "콜렉션"
    fun arr(){
        val arr: Array<Int> = Array(3,{0})
        for (i in 0 .. arr.size){
            Log.d(TAG, "arr크기: ${arr.size}   값:${arr[0]}")
        }
    }
    fun arrList(){
        var arrList = mutableListOf<Int>();//가변 리스트
        arrList.add(1)
        arrList.add(2)
        arrList.add(3)
        for (i in 0 .. arrList.size){
            Log.d(TAG, "arr크기: ${arrList.size}   값:${arrList[0]}")
        }
    }

    fun colMap(){
        var map = mapOf<String,String>(); //맵 만들기
        map["a"] to "b"
        Log.d(TAG, "맵출력: ${map.get("a")} ")

    }
}