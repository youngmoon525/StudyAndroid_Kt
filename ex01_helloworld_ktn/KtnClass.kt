package com.example.ex01_helloworld_ktn

import android.util.Log


class KtnClass {
    private var prVar = "aa"
    public var puVar = "bb"
    internal var inVar = "cc"
    var TAG = "클래스"
    var ivField = "멤버"
    class KtnClass(){ //<==주생성자
        init {
            Log.d("클래스", " 클래스 생성전에는 필드를 사용할수가없음. init은 특정 로직을 실행함")
        }
    }
    constructor(ivField: String){
        this.ivField = ivField //생성자

    }
    constructor(ivField: String , ivIntField:Int){
        this.ivField = ivField //생성자

    }
    fun methods(){
        Log.d(TAG, "methods: ")
    }

    fun makeClass(){
        var ktn = KtnClass("생성자에 넘겨줌")
        Log.d(TAG, "${ktn.ivField}")
        var ktn2 = KtnClass();
    }

}