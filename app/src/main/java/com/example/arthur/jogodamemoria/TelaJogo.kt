package com.example.arthur.jogodamemoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts


class TelaJogo : AppCompatActivity() {

    private lateinit var buttonComecarJogo: Button
    private lateinit var gridLayoutJogo: GridLayout
    private lateinit var todasImagens: Jogo
    private lateinit var progressBar1: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_jogo)

        gridLayoutJogo = findViewById(R.id.gridLayoutJogo)
        buttonComecarJogo = findViewById(R.id.botaoComecarJogo)

        this.progressBar1 = findViewById(R.id.progressBar1)
        progress()

        initializeGame()

        buttonComecarJogo.setOnClickListener {
            startNewGame()
        }
    }

    private fun initializeGame() {
        todasImagens = Jogo(this, gridLayoutJogo) {
            buttonComecarJogo.visibility = View.VISIBLE
        }
        todasImagens.inicieJogo()
        todasImagens.adicionarImagem()
        todasImagens.GridLayout()
    }

    private fun startNewGame() {
        buttonComecarJogo.visibility = View.GONE
        todasImagens.inicieJogo()
        todasImagens.adicionarImagem()
        todasImagens.GridLayout()
    }

    private fun progress() {
        Thread {
            while (this.progressBar1.progress < 100) {
                this.progressBar1.progress += 1
                Thread.sleep(700)
            }
            if (this.progressBar1.progress == 100) {
                progressBar1.progress = 0
                perdeu.launch(Intent(this, TelaPerdeu::class.java))
            }
            this.progressBar1.visibility = View.INVISIBLE
        }.start()
    }

    val perdeu = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
        }
    }
}
//    fun progress(){
//        Thread{
//            while (this.progressBar.progress < 100){
//                this.progressBar.progress += 1
//                Thread.sleep(500)
//            }
//            if(this.progressBar.progress == 100){
//                progressBar.progress = 0
//                perdeuResult.launch(Intent(this, PerdeuActivity::class.java))
//            }
//
////        }.start()
//    }
//}












//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_tela_jogo)
//
//
//
//}
//
//
//fun ganhou() {
//    val telaGanhou = Intent(this, /*Ganhou*/::class.java)
//    startActivity(telaGanhou)
//}
//
//fun perdeu() {
//    val telaPerdeu = Intent(this, /*Fim_Jogo*/::class.java)
//    startActivity(telaPerdeu)
//
//}












//val imagem1 = findViewById<ImageView>(R.id.imageView1)
//val imagem2 = findViewById<ImageView>(R.id.imageView2)
//val imagem3 = findViewById<ImageView>(R.id.imageView3)
//val imagem4 = findViewById<ImageView>(R.id.imageView4)
//val imagem5 = findViewById<ImageView>(R.id.imageView5)
//val imagem6 = findViewById<ImageView>(R.id.imageView6)
//val imagem7 = findViewById<ImageView>(R.id.imageView7)
//val imagem8 = findViewById<ImageView>(R.id.imageView8)
//val imagem9 = findViewById<ImageView>(R.id.imageView9)
//val imagem10 = findViewById<ImageView>(R.id.imageView10)
//val imagem11 = findViewById<ImageView>(R.id.imageView11)
//val imagem12 = findViewById<ImageView>(R.id.imageView12)
//val imagem13 = findViewById<ImageView>(R.id.imageView13)
//val imagem14 = findViewById<ImageView>(R.id.imageView14)
//val imagem15 = findViewById<ImageView>(R.id.imageView15)
//val imagem16 = findViewById<ImageView>(R.id.imageView16)
//
//
//val idxImagens = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8)
//idxImagens.shuffle()
//imagem1.tag = idxImagens[0]
//imagem2.tag = idxImagens[1]
//imagem3.tag = idxImagens[2]
//imagem4.tag = idxImagens[3]
//imagem5.tag = idxImagens[4]
//imagem6.tag = idxImagens[5]
//imagem7.tag = idxImagens[6]
//imagem8.tag = idxImagens[7]
//imagem9.tag = idxImagens[8]
//imagem10.tag = idxImagens[9]
//imagem11.tag = idxImagens[10]
//imagem12.tag = idxImagens[11]
//imagem13.tag = idxImagens[12]
//imagem14.tag = idxImagens[13]
//imagem15.tag = idxImagens[14]
//imagem16.tag = idxImagens[15]
//
//imagens = listOf(imagem1, imagem2, imagem3, imagem4, imagem5, imagem6, imagem7, imagem8, imagem9, imagem10, imagem11, imagem12, imagem13, imagem14, imagem15, imagem16)





