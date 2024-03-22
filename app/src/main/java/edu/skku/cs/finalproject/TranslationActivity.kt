package edu.skku.cs.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.IOException

class TranslationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        var items = ArrayList<Country> ()
        items.add(Country("Japan",0))
        items.add(Country("USA",1))
        items.add(Country("Germany",2))
        items.add(Country("England",3))
        items.add(Country("Austrailia",4))
        items.add(Country("Italy",5))
        items.add(Country("Spain",6))
        items.add(Country("France",7))
        val country = intent.getIntExtra(CheckActivity.EXT_CID,0)
        var flow = 0
        var tv = findViewById<TextView>(R.id.txtResult)
        tv.text=""
        var editStartT = findViewById<EditText>(R.id.etCity)
        val imgFrTo = findViewById<ImageView>(R.id.imgFrTo)
        imgFrTo.setImageResource(R.drawable.airplane)
        val txtMoney = findViewById<TextView>(R.id.txtSCity)
        val txtRes  = findViewById<TextView>(R.id.txtWeather)
        val fromImage = findViewById<ImageView>(R.id.imgKor)
        val fromText = findViewById<TextView>(R.id.txtStart)
        fromImage.setImageResource(R.drawable.korea)
        txtMoney.text= "KOR"
        fromText.text = "KOR"
        val toText = findViewById<TextView>(R.id.txtArrive)
        val toImage = findViewById<ImageView>(R.id.imgFor)
        when(country)
        {
            0->
            {
                txtRes.text="JPN"
                toText.text="JPN"
                toImage.setImageResource(R.drawable.japan)
            }
            1->{
                txtRes.text="USA"
                toText.text = "USA"
                toImage.setImageResource(R.drawable.usa)
            }
            2->{
                txtRes.text="GER"
                toText.text="GER"
                toImage.setImageResource(R.drawable.germany)
            }
            3->
            {
                txtRes.text="ENG"
                toText.text="ENG"
                toImage.setImageResource(R.drawable.uk)
            }
            4-> {
                txtRes.text="AST"
                toText.text="AST"
                toImage.setImageResource(R.drawable.australia)
            }5-> {
            txtRes.text="ITA"
            toText.text="ITA"
            toImage.setImageResource(R.drawable.italy)
        }6-> {
            txtRes.text="ESP"

            toText.text="ESP"
            toImage.setImageResource(R.drawable.spain)
        }7-> {
            txtRes.text="FRA"
            toText.text="FRA"
            toImage.setImageResource(R.drawable.france)
        }
        }
        val btn  = findViewById<Button>(R.id.btnChCon)
        btn.setOnClickListener {


            if(flow==0)
            {
                when(country)
                {
                    0->
                    {
                        txtMoney.text="JPN"
                        fromText.text="JPN"
                        fromImage.setImageResource(R.drawable.japan)
                    }
                    1->{
                        txtMoney.text="USA"
                        fromText.text = "USA"
                        fromImage.setImageResource(R.drawable.usa)
                    }
                    2->{
                        txtMoney.text="GER"
                        fromText.text="GER"
                        fromImage.setImageResource(R.drawable.germany)
                    }
                    3->
                    {
                        txtMoney.text="ENG"
                        fromText.text="ENG"
                        fromImage.setImageResource(R.drawable.uk)
                    }
                    4-> {
                        txtMoney.text="AST"
                        fromText.text="AST"
                        fromImage.setImageResource(R.drawable.australia)
                    }5-> {
                    txtMoney.text="ITA"
                    fromText.text="ITA"
                    fromImage.setImageResource(R.drawable.italy)
                }6-> {
                    txtMoney.text="ESP"
                    fromText.text="ESP"
                    fromImage.setImageResource(R.drawable.spain)
                }7-> {
                    txtMoney.text="FRA"
                    fromText.text="FRA"
                    fromImage.setImageResource(R.drawable.france)
                }
                }

                toImage.setImageResource(R.drawable.korea)
                toText.text="KOR"
                txtRes.text="KOR"
                flow=1
            }
            else
            {
                when(country)
                {
                0->
                {
                    txtRes.text="JPN"
                    toText.text="JPN"
                    toImage.setImageResource(R.drawable.japan)
                }
                1->{
                    txtRes.text="USA"
                    toText.text = "USA"
                    toImage.setImageResource(R.drawable.usa)
                }
                2->{
                    txtRes.text="GER"
                    toText.text="GER"
                    toImage.setImageResource(R.drawable.germany)
                }
                3->
                {
                    txtRes.text="ENG"
                    toText.text="ENG"
                    toImage.setImageResource(R.drawable.uk)
                }
                4-> {
                    txtRes.text="AST"
                    toText.text="AST"
                    toImage.setImageResource(R.drawable.australia)
                }5-> {
                    txtRes.text="ITA"
                    toText.text="ITA"
                    toImage.setImageResource(R.drawable.italy)
                }6-> {
                    txtRes.text="ESP"

                    toText.text="ESP"
                    toImage.setImageResource(R.drawable.spain)
                }7-> {
                    txtRes.text="FRA"
                    toText.text="FRA"
                    toImage.setImageResource(R.drawable.france)
                }
              }
                txtMoney.text= "KOR"
                fromImage.setImageResource(R.drawable.korea)
                fromText.text = "KOR"
                flow=0
            }

            tv.text=""
            editStartT.text.clear()
        }


        val translationBtn = findViewById<Button>(R.id.btnTrans)
        translationBtn.setOnClickListener {

            if(editStartT.text.toString().length > 0)
            {
                //post
                val JSON = "application/json; charset=utf-8".toMediaType()

                var url = "https://openapi.naver.com/v1/papago/n2mt"
                val client = OkHttpClient()

                val json = JSONObject()
                if(flow == 0)
                {
                    json.put("source", "ko")
                    when(country)
                    {
                        0->
                        {
                            json.put("target", "ja")

                        }
                        1->{
                            json.put("target", "en")
                        }
                        2->{
                            json.put("target", "de")
                        }
                        3->
                        {
                            json.put("target", "en")
                        }
                        4-> {
                            json.put("target", "en")
                        }5-> {
                        json.put("target", "it")
                        }6-> {
                            json.put("target", "es")
                        }7-> {
                            json.put("target", "fr")
                        }
                    }
                    json.put("text",editStartT.text.toString())

                }
                else if(flow == 1)
                {
                    when(country)
                    {
                        0->
                        {
                            json.put("source", "ja")

                        }
                        1->{
                            json.put("source", "en")
                        }
                        2->{
                            json.put("source", "de")
                        }
                        3->
                        {
                            json.put("source", "en")
                        }
                        4-> {
                            json.put("source", "en")
                        }5-> {
                            json.put("source", "it")
                        }6-> {
                            json.put("source", "es")
                        }7-> {
                            json.put("source", "fr")
                        }
                    }
                    json.put("target", "ko")

                    json.put("text",editStartT.text.toString())

                }
                val body = RequestBody.create(JSON, json.toString())
                val request = Request.Builder()
                    .header("X-Naver-Client-Id", "M0sTuKhKGYLTkYqaFL7q")
                    .addHeader("X-Naver-Client-Secret", "5U4Baa4hjE")
                    .url(url)
                    .post(body)
                    .build()

                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        TODO("Not yet implemented")
                    }
                    override fun onResponse(call: Call, response: Response) {

                        CoroutineScope(Dispatchers.Main).launch {
                            var str = response.body?.string()
                            val data = Gson().fromJson(str, Translated::class.java)
                            data.message.result.translatedText
                            println(data.message.result.translatedText)
                            tv.text= data.message.result.translatedText
                        }
                    }
                })
            }

        }
    }
}