package com.example.ibex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelecEmpresa : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selec_empresa)

        val siguiente = findViewById<Button>(R.id.btnEmpresa)

        siguiente.setOnClickListener {
            val i = Intent(this, SelecPeriodo::class.java)//metodo intent para entrar a la siguiente vista
            startActivity(i)
            finish()
        }
    }
}