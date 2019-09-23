package com.example.jsafk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var dp = OnBackDoublePressed(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomStudy.setOnClickListener { startActivity<RandomStudyActivity>() }
        unknown.setOnClickListener{ startActivity<ListActivity>("list" to App.prefs.arr) }
        termination.setOnClickListener {
            alert( "勉強しなきゃいけないことがもっと残ったら頑張ってやってみましょう！", "本当に終了しますか？"){
                yesButton {
                    finishAffinity()
                    System.runFinalization()
                    exitProcess(0)
                }
                noButton {  }
            }.show()
        }
    }

    override fun onBackPressed() {
        dp.onBackPressed()
    }
}
