package edu.skku.cs.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class CurrencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
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
        var currencyForKRW = 1.0
        var currencyForeign = 1.0
        var tv = findViewById<TextView>(R.id.txtResult)
        tv.text="0"
        var editKrw = findViewById<EditText>(R.id.etCity)
        val client = OkHttpClient()
        val host = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/krw.json"
        val req = Request.Builder().url(host).build()
            client.newCall(req).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    response.use{
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        val str = response.body!!.string()
                        val data = Gson().fromJson(str, Money::class.java)
                        CoroutineScope(Dispatchers.Main).launch {
                            var values = 1.0

                            when(country)
                            {
                                0->
                                {
                                    values = data.krw.jpy
                                }
                                1->{
                                    values = data.krw.usd
                                }
                                2->{
                                    values = data.krw.eur
                                }
                                3->
                                {
                                    values = data.krw.gbp
                                }
                                4-> {
                                    values = data.krw.aud
                                }5-> {
                                values = data.krw.eur
                                }6-> {
                                values = data.krw.eur
                                }7-> {
                                values = data.krw.eur
                                }
                            }

                            currencyForKRW = (1/values)
                            currencyForeign = values

                        }

                    }
                }
            })
        val imgFrTo = findViewById<ImageView>(R.id.imgFrTo)
        imgFrTo.setImageResource(R.drawable.airplane)
        val txtMoney = findViewById<TextView>(R.id.txtSCity)
        val txtRes  = findViewById<TextView>(R.id.txtWeather)
        val fromImage = findViewById<ImageView>(R.id.imgKor)
        val fromText = findViewById<TextView>(R.id.txtStart)
        fromImage.setImageResource(R.drawable.korea)
        txtMoney.text= "KRW"
        fromText.text = "KOR"
        val toText = findViewById<TextView>(R.id.txtArrive)
        val toImage = findViewById<ImageView>(R.id.imgFor)
        when(country)
        {
            0->
            {
                txtRes.text="JPY"
                toText.text="JPN"
                toImage.setImageResource(R.drawable.japan)
            }
            1->{
                txtRes.text="USD"
                toText.text = "USA"
                toImage.setImageResource(R.drawable.usa)
            }
            2->{
                txtRes.text="EUR"
                toText.text="GER"
                toImage.setImageResource(R.drawable.germany)
            }
            3->
            {
                txtRes.text="GBP"
                toText.text="ENG"
                toImage.setImageResource(R.drawable.uk)
            }
            4-> {
                txtRes.text="AUD"
                toText.text="AST"
                toImage.setImageResource(R.drawable.australia)
            }5-> {
            txtRes.text="EUR"
            toText.text="ITA"
            toImage.setImageResource(R.drawable.italy)
        }6-> {
            txtRes.text="EUR"

            toText.text="ESP"
            toImage.setImageResource(R.drawable.spain)
        }7-> {
            txtRes.text="EUR"
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
                        txtMoney.text="JPY"
                        fromText.text="JPN"
                        fromImage.setImageResource(R.drawable.japan)
                    }
                    1->{
                        txtMoney.text="USD"
                        fromText.text = "USA"
                        fromImage.setImageResource(R.drawable.usa)
                    }
                    2->{
                        txtMoney.text="EUR"
                        fromText.text="GER"
                        fromImage.setImageResource(R.drawable.germany)
                    }
                    3->
                    {
                        txtMoney.text="GBP"
                        fromText.text="ENG"
                        fromImage.setImageResource(R.drawable.uk)
                    }
                    4-> {
                        txtMoney.text="AUD"
                        fromText.text="AST"
                        fromImage.setImageResource(R.drawable.australia)
                    }5-> {
                        txtMoney.text="EUR"
                        fromText.text="ITA"
                        fromImage.setImageResource(R.drawable.italy)
                    }6-> {
                        txtMoney.text="EUR"
                        fromText.text="ESP"
                        fromImage.setImageResource(R.drawable.spain)
                    }7-> {
                        txtMoney.text="EUR"
                        fromText.text="FRA"
                        fromImage.setImageResource(R.drawable.france)
                        }
                }

                toImage.setImageResource(R.drawable.korea)
                toText.text="KOR"
                txtRes.text="KRW"
                flow=1
            }
            else
            {when(country)
            {
                0->
                {
                    txtRes.text="JPY"
                    toText.text="JPN"
                    toImage.setImageResource(R.drawable.japan)
                }
                1->{
                    txtRes.text="USD"
                    toText.text = "USA"
                    toImage.setImageResource(R.drawable.usa)
                }
                2->{
                    txtRes.text="EUR"
                    toText.text="GER"
                    toImage.setImageResource(R.drawable.germany)
                }
                3->
                {
                    txtRes.text="GBP"
                    toText.text="ENG"
                    toImage.setImageResource(R.drawable.uk)
                }
                4-> {
                    txtRes.text="AUD"
                    toText.text="AST"
                    toImage.setImageResource(R.drawable.australia)
                }5-> {
                    txtRes.text="EUR"
                    toText.text="ITA"
                    toImage.setImageResource(R.drawable.italy)
                }6-> {
                    txtRes.text="EUR"

                    toText.text="ESP"
                    toImage.setImageResource(R.drawable.spain)
                }7-> {
                    txtRes.text="EUR"
                    toText.text="FRA"
                    toImage.setImageResource(R.drawable.france)
                }
            }
                txtMoney.text= "KRW"
                fromImage.setImageResource(R.drawable.korea)
                fromText.text = "KOR"
                flow=0
            }

            tv.text="0"
            editKrw.text.clear()
        }

        editKrw.addTextChangedListener( object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                CoroutineScope(Dispatchers.Main).launch {
                    if(p0.toString().length > 0)
                    {
                        if(flow == 0)
                        {
                            var valueKrw = p0.toString()!!.toDouble()
                            var valuesToChange = valueKrw * currencyForeign
                            tv.text=valuesToChange.toString()

                        }
                        else if(flow == 1)
                        {
                            var valueFor = p0.toString()!!.toDouble()
                            var valuesToChange = valueFor * currencyForKRW
                            tv.text=valuesToChange.toString()
                        }

                    }
                    else
                    {
                        tv.text="0"
                    }
                }

            }

        }
        )




    }
}