package com.example.arthur.jogodamemoria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TelaGanhou : AppCompatActivity() {
    private lateinit var botaoNovoJogo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_ganhou)

        this.botaoNovoJogo = findViewById(R.id.botaoNovoJogo)

        this.botaoNovoJogo.setOnClickListener {
            val intent = Intent(this, TelaJogo:: class.java)
            startActivity(intent)
            finish()
        }
    }
}