package com.example.jsafk_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_random_study.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class RandomStudyActivity : AppCompatActivity() {
    val set: MutableSet<Int> = mutableSetOf()
    var hashCount: Int = 0
    var randomNumber: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_study)

        randomKanji()

        unknownKanji.setOnClickListener {
            var list = App.prefs.arr
            Log.d("check listsize = ", list.size.toString())
            for(i in 0 until list.size){
                Log.d("check list = ", list[i].toString())
            }
            if(!list.contains(randomNumber)){
                list.add(randomNumber)
                Log.d("check real listsize = ", list.size.toString())
                App.prefs.arr = list
                list = App.prefs.arr
                Log.d("check comp listsize = ", list.size.toString())

                toast("リストに追加しました！")
            }else{
                toast("もうリストに追加しました漢字です。")
            }
        }

        knowKanji.setOnClickListener { randomKanji() }
        knowMeaning.setOnClickListener {  }
        knowPronounce.setOnClickListener {  }
        showList.setOnClickListener{
            val list = set.toList()
            if(list.size == 1) startActivity<EmptyActivity>("empty" to "学習した漢字がありません。")
            else startActivity<ListActivity>("list" to list)
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("学習終了")
            .setMessage("学習を終了したら今日の漢字が削除されます。")
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
            val inputStream: InputStream = assets.open("kanji.json")
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
                randomNumber = (Math.random() * 600).toInt() + 1
                set.add(randomNumber)
            }
            hashCount++
            val obj2: JSONObject = kanjiArray.getJSONObject(randomNumber)
            val kanji: String = obj2.getString("kanji")
            baseView.text = kanji
            count.text = "$hashCount/600"
        }catch(e : JSONException){
            e.printStackTrace()
        }
    }
}

