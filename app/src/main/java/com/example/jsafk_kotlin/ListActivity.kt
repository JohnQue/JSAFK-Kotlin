package com.example.jsafk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_random_study.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val list = intent.getIntegerArrayListExtra("list")
        val itemList = ArrayList<ItemData>()
        val size = list.size
        if(size == 1){
            startActivity<EmptyActivity>()
        }else {
            for (i in 0 until size - 1) itemList.add(getKanji(list[i])!!)
            val adapter = RecyclerAdapter(itemList)
            val linearLayoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = adapter
        }
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

    private fun getKanji(num: Int): ItemData?{
        try{
            val obj = JSONObject(loadJSONFromAsset())
            val kanjiValue: String = obj.getString("hanza")
            val kanjiArray = JSONArray(kanjiValue)
            val obj2: JSONObject = kanjiArray.getJSONObject(num)
            val kanji: String = obj2.getString("kanji")
            val meaning: String = obj2.getString("meaning")
            return ItemData(kanji, meaning)
        }catch(e : JSONException){
            e.printStackTrace()
        }
        return null
    }
}
