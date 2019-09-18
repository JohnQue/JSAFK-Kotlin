package com.example.jsafk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_random_study.*
import org.jetbrains.anko.toast

class RandomStudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_study)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("学習終了")
            .setPositiveButton("終了"){ _, _ ->
                finish()
            }
            .setNegativeButton("キャンセル"){ _, _ ->

            }
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

        }

        return super.onOptionsItemSelected(item)
    }
}

