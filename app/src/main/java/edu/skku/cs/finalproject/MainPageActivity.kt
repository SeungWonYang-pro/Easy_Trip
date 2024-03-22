package edu.skku.cs.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout

class MainPageActivity : AppCompatActivity() {
    companion object {
        const val EXT_COUNTRY  = "COUNTRY ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        var textView = findViewById<TextView>(R.id.textUserName)
        var name = intent.getStringExtra(LoginActivity.EXT_NAME)
        textView.text = name + " 's EASY TRIP!"
        var koreaImage = findViewById<ImageView>(R.id.imageKorea)
        koreaImage.setImageResource(R.drawable.korea)
        var toImage  = findViewById<ImageView>(R.id.imageTo)
        toImage.setImageResource(R.drawable.japan)
        var toText = findViewById<TextView>(R.id.textTo)
        toText.text="JPN"
        var curCid = 0
        var plainImage = findViewById<ImageView>(R.id.ImagePlane)
        plainImage.setImageResource(R.drawable.airplane)
        var items = ArrayList<Country> ()
        items.add(Country("Japan",0))
        items.add(Country("USA",1))
        items.add(Country("Germany",2))
        items.add(Country("England",3))
        items.add(Country("Austrailia",4))
        items.add(Country("Italy",5))
        items.add(Country("Spain",6))
        items.add(Country("France",7))


        var btnGo = findViewById<Button>(R.id.btnGo)

        btnGo.setOnClickListener {
            val intent = Intent(this,CheckActivity::class.java).apply {
                putExtra(EXT_COUNTRY,curCid)
            }
            startActivity(intent)

        }





        var selectedIndex = 0
        val motionLayout = findViewById<MotionLayout>(R.id.cmotionConstraint)

        val v1 = findViewById<View>(R.id.v1)
        val v2 = findViewById<View>(R.id.v2)
        val v3 = findViewById<View>(R.id.v3)
        val v4 = findViewById<View>(R.id.v4)
        val v5 = findViewById<View>(R.id.v5)
        val v6 = findViewById<View>(R.id.v6)
        val v7 = findViewById<View>(R.id.v7)
        val v8 = findViewById<View>(R.id.v8)
        val v1Txt = v1.findViewById<TextView>(R.id.v1TxT)
        val v2Txt = v2.findViewById<TextView>(R.id.v2TxT)
        val v3Txt = v3.findViewById<TextView>(R.id.v3TxT)

        v1.setOnClickListener {
            if (selectedIndex == 0) {
                return@setOnClickListener
            }

            motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
            motionLayout.transitionToEnd()
            selectedIndex = 0
            curCid=0
            changeImg(curCid)
        }

        v2.setOnClickListener {
            if (selectedIndex == 1)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 2) {
                motionLayout.setTransition(R.id.s3, R.id.s2)  //red to orange transition
            } else if(selectedIndex == 0) {
                motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 1
            curCid=1
            changeImg(curCid)
        }

        v3.setOnClickListener {
            if (selectedIndex == 2)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 3) {
                motionLayout.setTransition(R.id.s4, R.id.s3)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s2, R.id.s3) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 2
            curCid=2
            changeImg(curCid)

        }

        v4.setOnClickListener {
            if (selectedIndex == 3)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 4) {
                motionLayout.setTransition(R.id.s5, R.id.s4)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s3, R.id.s4) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 3
            curCid=3
            changeImg(curCid)

        }

        v5.setOnClickListener {
            if (selectedIndex == 4)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 5) {
                motionLayout.setTransition(R.id.s6, R.id.s5)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s4, R.id.s5) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 4
            curCid=4
            changeImg(curCid)

        }

        v6.setOnClickListener {
            if (selectedIndex == 5)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 6) {
                motionLayout.setTransition(R.id.s7, R.id.s6)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s5, R.id.s6) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 5
            curCid=5
            changeImg(curCid)

        }


        v7.setOnClickListener {
            if (selectedIndex == 6)
            {
                return@setOnClickListener
            }

            if (selectedIndex == 7) {
                motionLayout.setTransition(R.id.s8, R.id.s7)  //red to orange transition
            } else {
                motionLayout.setTransition(R.id.s6, R.id.s7) //blue to orange transition
            }
            motionLayout.transitionToEnd()
            selectedIndex = 6;
            curCid=6
            changeImg(curCid)

        }


        v8.setOnClickListener {
            if (selectedIndex == 7){
                return@setOnClickListener
            }

            motionLayout.setTransition(R.id.s7, R.id.s8) //orange to red transition
            motionLayout.transitionToEnd()
            selectedIndex = 7;
            curCid=7
            changeImg(curCid)
        }
    }

    fun changeImg( curCid : Int){
        var toImage  = findViewById<ImageView>(R.id.imageTo)
        var toText = findViewById<TextView>(R.id.textTo)

        when(curCid)
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
    }
}