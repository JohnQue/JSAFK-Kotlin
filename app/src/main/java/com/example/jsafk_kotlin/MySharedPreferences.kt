package com.example.jsafk_kotlin

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONArray
import org.json.JSONException

class MySharedPreferences(context: Context){
    private val PREFS_FILENAME = "prefs"
    private val PREF_KEY_MY_ARRAY = "arr"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var arr: MutableList<Int>
        get(){
            val json = prefs.getString(PREF_KEY_MY_ARRAY, "")
            Log.d("check = jsonString : ", json)
            val list = mutableListOf<Int>()
            if(json != null){
                try{
                    val jsonArray = JSONArray(json)
                    val size = jsonArray.length()
                    for(i in 0 until size) list.add(jsonArray.optInt(i))
                }catch(e: JSONException){
                    e.printStackTrace()
                }
            }
            return list
        }
        set(value){
            val size = value.size
            Log.d("check = size : ", size.toString())
            val jsonArray = JSONArray()
            for(i in 0 until size) jsonArray.put(value[i])
            Log.d("check = Array : ", jsonArray.toString())
            if(value.isNotEmpty()) prefs.edit().putString(PREF_KEY_MY_ARRAY, jsonArray.toString()).apply()
            else prefs.edit().putString(PREF_KEY_MY_ARRAY, null).apply()
        }
}