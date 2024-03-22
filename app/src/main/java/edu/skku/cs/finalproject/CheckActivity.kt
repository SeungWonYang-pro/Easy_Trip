package edu.skku.cs.finalproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckActivity : AppCompatActivity() {
    companion object {
        const val EXT_CID  = "COUNTRY ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        var items = ArrayList<Country> ()
        items.add(Country("Japan",0))
        items.add(Country("USA",1))
        items.add(Country("Germany",2))
        items.add(Country("England",3))
        items.add(Country("Austrailia",4))
        items.add(Country("Italy",5))
        items.add(Country("Spain",6))
        items.add(Country("France",7))
        var country = intent.getIntExtra(MainPageActivity.EXT_COUNTRY,0)

        var imgGoing = findViewById<ImageView>(R.id.imgGoing)
        var imgKorea = findViewById<ImageView>(R.id.imgKorea)
        var imgPlane = findViewById<ImageView>(R.id.imgPlane)
        var toCount = findViewById<TextView>(R.id.txtToCountry)
        imgKorea.setImageResource(R.drawable.korea)
        imgPlane.setImageResource(R.drawable.airplane)
        when(country)
        {
            0->
            {
                toCount.text="JPN"
                imgGoing.setImageResource(R.drawable.japan)
            }
            1->{
                toCount.text = "USA"
                imgGoing.setImageResource(R.drawable.usa)
            }
            2->{
                toCount.text="GER"
                imgGoing.setImageResource(R.drawable.germany)
            }
            3->
            {
                toCount.text="ENG"
                imgGoing.setImageResource(R.drawable.uk)
            }
            4-> {
                toCount.text="AST"
                imgGoing.setImageResource(R.drawable.australia)
            }5-> {
            toCount.text="ITA"
            imgGoing.setImageResource(R.drawable.italy)
           }6-> {
            toCount.text="ESP"
            imgGoing.setImageResource(R.drawable.spain)
           }7-> {
            toCount.text="FRA"
            imgGoing.setImageResource(R.drawable.france)
             }
        }

/*
        var btnGo = findViewById<Button>(R.id.btnCur)

        btnGo.setOnClickListener {
            val intent = Intent(this,CurrencyActivity::class.java).apply {
                putExtra(EXT_CID,country)
            }
            startActivity(intent)

        }*/








        var selectedIndex = 0
        val motionLayout = findViewById<MotionLayout>(R.id.motionConstraint)

        val v1 = findViewById<View>(R.id.v1)
        val v2 = findViewById<View>(R.id.v2)
        val v3 = findViewById<View>(R.id.v3)
        val v1Txt = v1.findViewById<TextView>(R.id.v1TxT)
        val v2Txt = v2.findViewById<TextView>(R.id.v2TxT)
        val v3Txt = v3.findViewById<TextView>(R.id.v3TxT)

        v1Txt.text="Money Exchange"
        v1.setOnClickListener {
            if (selectedIndex == 0) {
                val intent = Intent(this,CurrencyActivity::class.java).apply {
                    putExtra(EXT_CID,country)
                }
                startActivity(intent)
                return@setOnClickListener
            }

            motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
            motionLayout.transitionToEnd()
            selectedIndex = 0;
        }
        v2Txt.text = "Translation"

        v2.setOnClickListener {
            if (selectedIndex == 1)
            {
                val intent = Intent(this,TranslationActivity::class.java).apply {
                    putExtra(EXT_CID,country)
                }
                startActivity(intent)
                return@setOnClickListener
            }

            if (selectedIndex == 2) {
                motionLayout.setTransition(R.id.s3, R.id.s2)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 1;
        }


        v3Txt.text="Weather"
        v3.setOnClickListener {
            if (selectedIndex == 2){

                val intent = Intent(this,WeatherActivity::class.java).apply {
                    putExtra(EXT_CID,country)
                }
                startActivity(intent)
                return@setOnClickListener
            }

            motionLayout.setTransition(R.id.s2, R.id.s3) //orange to red transition
            motionLayout.transitionToEnd()
            selectedIndex = 2;
        }


    }


}