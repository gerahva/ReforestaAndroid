package com.example.reforestaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        findViewById<Button>(R.id.registrar).setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
         findViewById<Button>(R.id.listado).setOnClickListener {
                startActivity(Intent(applicationContext, ListadoActivity::class.java))
        }
    }
}