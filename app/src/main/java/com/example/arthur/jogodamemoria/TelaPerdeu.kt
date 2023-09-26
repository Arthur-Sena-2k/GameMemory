package com.example.arthur.jogodamemoria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TelaPerdeu : AppCompatActivity() {
    private lateinit var botaoJogarNovamente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_perdeu)

        this.botaoJogarNovamente = findViewById(R.id.botaoJogarNovamente)

        this.botaoJogarNovamente.setOnClickListener {
            val intent = Intent(this, TelaJogo::class.java)
            startActivity(intent)
            finish()
        }
    }
}