package com.example.jsafk_kotlin

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_random_study.*
import org.jetbrains.anko.startActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*

class RandomStudyActivity : AppCompatActivity() {
    val set: MutableSet<Int> = mutableSetOf()
    var hashCount: Int = 0
    var randomNumber: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_study)

        randomKanji()

        unknownKanji.setOnClickListener {  }
        knowKanji.setOnClickListener { randomKanji() }
        knowMeaning.setOnClickListener {  }
        knowPronounce.setOnClickListener {  }
        showList.setOnClickListener{
            val list = set.toList()
            startActivity<ListActivity>("list" to list )
        }
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

    private fun loadJSONFromAsset(): String{
        var json = ""
        try{
            val inputStream: InputStream = getAssets().open("kanji.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        }catch(e: IOException){
            e.printStackTrace()
        }
        return json
    }

    private fun randomKanji(){
        try{
            val obj = JSONObject(loadJSONFromAsset())
            val kanjiValue: String = obj.getString("hanza")
            val kanjiArray = JSONArray(kanjiValue)
            while(set.size == hashCount){
                randomNumber = (Math.random() * 500).toInt() + 1
                set.add(randomNumber)
            }
            hashCount++
            val obj2: JSONObject = kanjiArray.getJSONObject(randomNumber)
            val kanji: String = obj2.getString("kanji")
            baseView.text = kanji
            count.text = "$hashCount/500"
        }catch(e : JSONException){
            e.printStackTrace()
        }
    }
}

