package com.example.ibex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ibex.R

class SelecPeriodo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selec_periodo)

        val siguiente = findViewById<Button>(R.id.btnPeriodo)

        siguiente.setOnClickListener {
            val i = Intent(this, MenuPrincipal::class.java)//metodo intent para entrar a la siguiente vista
            startActivity(i)
            finish()
        }
    }
}