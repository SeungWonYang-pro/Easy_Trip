package edu.skku.cs.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        var textView = findViewById<TextView>(R.id.textWeather)
        var edText = findViewById<EditText>(R.id.etCity)
        val country = intent.getIntExtra(CheckActivity.EXT_CID,0)
        val toText = findViewById<TextView>(R.id.txtArrive)
        val toImage = findViewById<ImageView>(R.id.imgFor)
        val txtCity = findViewById<TextView>(R.id.txtSCity)
        val fromImage = findViewById<ImageView>(R.id.imgKor)
        val resText = findViewById<TextView>(R.id.txtWeather)
        val imgFrTo = findViewById<ImageView>(R.id.imgFrTo)
        imgFrTo.setImageResource(R.drawable.airplane)
        fromImage.setImageResource(R.drawable.korea)
        resText.text="NWS"
        txtCity.text="CITY"
        textView.text=""
        when(country)
        {
            0->
            {

                toText.text="JPN"
                toImage.setImageResource(R.drawable.japan)
            }
            1->{
                toText.text = "USA"
                toImage.setImageResource(R.drawable.usa)
            }
            2->{
                toText.text="GER"
                toImage.setImageResource(R.drawable.germany)
            }
            3->
            {
                toText.text="ENG"
                toImage.setImageResource(R.drawable.uk)
            }
            4-> {
                toText.text="AST"
                toImage.setImageResource(R.drawable.australia)
            }5-> {
            toText.text="ITA"
            toImage.setImageResource(R.drawable.italy)
            }6-> {

            toText.text="ESP"
            toImage.setImageResource(R.drawable.spain)
            }7-> {
            toText.text="FRA"
            toImage.setImageResource(R.drawable.france)
           }
        }
        val ttStart = findViewById<TextView>(R.id.txtStart)
        ttStart.text="KOR"
        val btn  = findViewById<Button>(R.id.btnFindWeather)
        btn.setOnClickListener {

            val client = OkHttpClient()
            val host = "https://api.weatherapi.com/v1/current.json"

            val api_key="apikey"
            val find_city=edText.text.toString()
            val path = "?key=" + api_key + "&q=" + find_city
            val req = Request.Builder().url(host+path).build()
            client.newCall(req).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    response.use{
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        val data = response.body!!.string()
                        val dat_parse = Gson().fromJson(data, DataModel::class.java)
                        val condition_find=dat_parse.current.condition.text
                        val temp_c=dat_parse.current.temp_c
                        CoroutineScope(Dispatchers.Main).launch {
                            var concat: String = condition_find.toString() + " and temperature is " + temp_c.toString()
                            textView.text = concat
                        }
                    }
                }
            })
        }


    }
}
