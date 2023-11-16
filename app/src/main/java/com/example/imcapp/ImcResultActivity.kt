package com.example.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.DecimalFormat

class ImcResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)
        initComponents()
        initListeners()
        var resul = intent.getDoubleExtra("RESULTADO_EXTRA", 0.0)
        var num:String = DecimalFormat("#.##").format(resul)
        var titl = intent.getStringExtra("TITULO_EXTRA") ?: ""
        var textres = intent.getStringExtra("TEXTO_EXTRA") ?: ""

        titulo.text = titl
        res.text = num
        desc.text = textres

    }

    private lateinit var boton2: AppCompatButton
    private lateinit var titulo: TextView
    private lateinit var res: TextView
    private lateinit var desc: TextView




    private fun initComponents() {
        boton2 = findViewById(R.id.boton2)
        titulo = findViewById(R.id.titulo)
        res = findViewById(R.id.res)
        desc = findViewById(R.id.desc)
    }

        private fun initListeners(){

            boton2.setOnClickListener {
                val intent = Intent(this, IMCApp::class.java)
                startActivity(intent)
            }

    }

    private fun calculosTotales(){



    }

}