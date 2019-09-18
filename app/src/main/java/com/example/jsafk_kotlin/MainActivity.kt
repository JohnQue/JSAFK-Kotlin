package com.example.jsafk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var dp = onBackDoublePressed(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomStudy.setOnClickListener { startActivity<RandomStudyActivity>() }
    }

    override fun onBackPressed() {
        dp.onBackPressed()

    }
}
