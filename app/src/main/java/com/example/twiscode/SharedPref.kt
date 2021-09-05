package com.example.twiscode

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref (activity: Activity){
    val mypref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun clear(){
        sp.edit().clear().apply()
    }
}
