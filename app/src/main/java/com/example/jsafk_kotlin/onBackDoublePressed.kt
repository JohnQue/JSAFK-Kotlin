package com.example.jsafk_kotlin

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.System.exit

open class onBackDoublePressed(activity: AppCompatActivity){
    private var backKeyPressedTime: Long = 0
    private var activity: Activity = activity

    open fun onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis()
            showGuide()
            return
        }else {
            activity.finishAffinity()
            System.runFinalization()
            exit(0)
        }
    }

    open fun showGuide(){
        Toast.makeText(activity, "もう一度押したら終了となります。", Toast.LENGTH_LONG).show()
    }
}