package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCApp : AppCompatActivity(){




        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_imcapp)
            initComponents()
            initListeners()
            initUI()


        }

        private lateinit var viewMale: CardView
        private lateinit var viewFemale: CardView
        private lateinit var rsHeight: RangeSlider
        private lateinit var tvHeight: TextView
        private lateinit var txPeso: TextView
        private lateinit var txED: TextView
        private lateinit var btnAdd1: FloatingActionButton
        private lateinit var btnAdd2: FloatingActionButton
        private lateinit var btnRemove1: FloatingActionButton
        private lateinit var btnRemove2: FloatingActionButton
        private lateinit var boton:AppCompatButton
        private var peso: Int = 20
        private var edad: Int = 26
        private var altura: Int = 120



    private var isMaleSelected: Boolean = true

        private fun initComponents() {
            viewMale = findViewById(R.id.viewMale)
            viewFemale = findViewById(R.id.viewFemale)
            rsHeight = findViewById(R.id.rsHeight)
            tvHeight = findViewById(R.id.tvHeight)
            txPeso = findViewById(R.id.txPeso)
            txED = findViewById(R.id.txED)
            btnAdd1 = findViewById(R.id.btnAdd1)
            btnAdd2 = findViewById(R.id.btnAdd2)
            btnRemove1 = findViewById(R.id.btnRemove1)
            btnRemove2 = findViewById(R.id.btnRemove2)
            boton = findViewById(R.id.boton)

        }



        private fun initListeners(){
            viewMale.setOnClickListener(){
                setGenderColor()
                isMaleSelected = true}

            viewFemale.setOnClickListener{
                setGenderColor()
                isMaleSelected = false}


            rsHeight.addOnChangeListener { _, value, _ ->
                //tvHeight.text = value.toString()
                tvHeight.text = DecimalFormat("#.##").format(value)
            }

              btnAdd1.setOnClickListener{
              peso = peso + 1
                setPeso()
              }


             btnAdd2.setOnClickListener{
              edad = edad + 1
               setEdad()
              }


              btnRemove1.setOnClickListener{
                 peso = peso - 1
                 setPeso()
              }


              btnRemove2.setOnClickListener{
                edad = edad - 1
                setEdad()
             }


            boton.setOnClickListener {
                calculateIMC()
            }



        }

        private fun setGenderColor(){
            viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
            viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
        }

        private fun getBackgroundColor(isComponentSelected : Boolean): Int{
            val colorReference = if(isComponentSelected) {
                R.color.bg_comp_sel
            } else {
                R.color.bg_comp
            }
            return ContextCompat.getColor(this,colorReference)
        }

        private fun initUI(){
            setGenderColor()
        }

      private fun setPeso(){
         txPeso.text = peso.toString()
    }

    private fun setEdad(){
         txED.text = edad.toString()
     }
    private fun setAltura(){
        tvHeight.text = altura.toString()
    }



    private fun calculateIMC(){

        var peso = txPeso.text.toString().toDouble()
        var altura = tvHeight.text.toString().toDouble()
        altura = altura/100
        var imc:Double = peso/(altura*altura)
        //var num:String = DecimalFormat("#.##").format(imc)

        var descripcion: String = ""
        var titulo: String = ""


        when (imc) {
            in 0.0..18.5 -> {
                descripcion = getString(R.string.TextInfrapeso)
                titulo = getString(R.string.Infrapeso)
            }

            in 18.6..24.9 -> {
                descripcion = getString(R.string.TextNormal)
                titulo = getString(R.string.PesoNormal)
            }

            in 25.00..29.9 -> {
                descripcion = getString(R.string.TextSobrepeso)
                titulo = getString(R.string.Sobrepeso)
            }

            in 30.00..Double.MAX_VALUE -> {
                descripcion = getString(R.string.TextObesidad)
                titulo = getString(R.string.Obesidad)
            }

        }
        val intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra("RESULTADO_EXTRA", imc)
        intent.putExtra("TITULO_EXTRA", titulo)
        intent.putExtra("TEXTO_EXTRA", descripcion)
        startActivity(intent)

    }
}

