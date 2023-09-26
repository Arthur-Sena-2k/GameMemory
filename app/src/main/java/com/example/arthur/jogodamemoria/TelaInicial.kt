package com.example.arthur.jogodamemoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TelaInicial : AppCompatActivity() {

    private lateinit var botaoIniciarJogo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        this.botaoIniciarJogo = findViewById(R.id.buttonPlay)

        this.botaoIniciarJogo.setOnClickListener {
            val intent = Intent(this, TelaJogo::class.java )
            startActivity(intent)
            finish()
        }

    }

//    fun vaiTelaJogo(){
//        val intent = Intent(this, TelaJogo::class.java)
//        startActivity(intent)
//    }
}
