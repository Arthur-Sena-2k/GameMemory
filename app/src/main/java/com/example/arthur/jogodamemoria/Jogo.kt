package com.example.arthur.jogodamemoria

import android.content.Intent
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView

class Jogo (private val context: Context, private val gridLayout: GridLayout, private val onGameComplete: () -> Unit) {

    private val imageResources = arrayOf(
        R.drawable.i1,
        R.drawable.i2,
        R.drawable.i3,
        R.drawable.i4,
        R.drawable.i5,
        R.drawable.i6,
        R.drawable.i7,
        R.drawable.i8
    )

    private val imagemList = mutableListOf<Imagem>()
    private val imagensViradas = mutableListOf<Imagem>()
    private var iguais = 0
    private val handler = Handler(Looper.getMainLooper())

    init {
        inicieJogo()
    }

    fun inicieJogo() {
        imagemList.clear()
        imageResources.shuffle()

        for (i in 0 until 16) {
            val imagem = Imagem(i, imageResources[i % imageResources.size])
            imagemList.add(imagem)
        }
        imagemList.shuffle()
    }

    fun adicionarImagem() {
        for (i in 1..16) {
            val imageViewId = context.resources.getIdentifier("imageView$i", "id", context.packageName)
            val imageView = gridLayout.findViewById<ImageView>(imageViewId)
            val imagem = imagemList[i - 1]

            if (imagem.imgVirada) {
                imageView.setImageResource(imagem.imageResId)
            } else {
                imageView.setImageResource(R.drawable.imgback)
            }

            imageView.setOnClickListener {
                onImagemClicked(imagem, imageView)
            }
        }
    }

    private fun onImagemClicked(imagemSelecionanda: Imagem, imageView: ImageView) {
        if (!imagemSelecionanda.imgVirada && imagensViradas.size < 2) {
            imagemSelecionanda.imgVirada = true
            imageView.setImageResource(imagemSelecionanda.imageResId)
            imagensViradas.add(imagemSelecionanda)

            if (imagensViradas.size == 2) {
                val (card1, card2) = imagensViradas

                if (card1.imageResId == card2.imageResId) {
                    card1.imgIgual = true
                    card2.imgIgual = true
                    imagensViradas.clear()

                    iguais++
                    ValidarTodosAsImagens()
                } else {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        imagensViradas.forEach {
                            it.imgVirada = false
                            val imagemImageView = encontrarImagem(it.id)
                            imagemImageView?.setImageResource(R.drawable.imgback)
                        }
                        imagensViradas.clear()
                        GridLayout()
                    }, 500)
                }
            }
        }
    }

    private fun encontrarImagem(imagemId: Int): ImageView? {
        val imageViewId = context.resources.getIdentifier("imageView$imagemId", "id", context.packageName)
        return gridLayout.findViewById(imageViewId)
    }

    fun GridLayout() {
        for (i in 1..16) {
            val imageView = encontrarImagem(i)
            if (imageView != null) {
                val imagem = imagemList[i - 1]

                if (imagem.imgVirada) {
                    imageView.setImageResource(imagem.imageResId)
                } else {
                    imageView.setImageResource(R.drawable.imgback)
                }
            }
        }

        ValidarTodosAsImagens()
    }

    private fun ValidarTodosAsImagens() {
        if (iguais == imagemList.size / 2) {
            // Todas as cartas estão emparelhadas, o jogador ganhou.
            val intent = Intent(context, TelaGanhou::class.java)
            context.startActivity(intent)
        } else if (imagensViradas.size == 2 && !imagensViradas[0].imgIgual && !imagensViradas[1].imgIgual) {
            // O jogador perdeu, inicie a tela de derrota.
            val intent = Intent(context, TelaPerdeu::class.java)
            context.startActivity(intent)
        }
    }

}








//
//private fun criarMatriz() {
//    for (i in 0 until 4) {
//        val linha: MutableList<Imagem?> = mutableListOf()
//        for (j in 0 until 4) {
//            linha.add(null)
//        }
//        matriz.add(linha)
//    }
//}
//
//private fun embaralharCartas() {
//    val imagensParaEmbaralhar = imagens.shuffled().take(8)
//    val cartasDuplas = (imagensParaEmbaralhar + imagensParaEmbaralhar).shuffled()
//    for (i in 0 until 4) {
//        for (j in 0 until 4) {
//            matriz[i][j] = cartasDuplas[i * 4 + j]
//        }
//    }
//}
//
//fun revelarCarta(linha: Int, coluna: Int): Imagem? {
//    if (matriz[linha][coluna]?.imagemVirada == false) {
//        matriz[linha][coluna]?.virar()
//        cartasViradas++
//        return matriz[linha][coluna]
//    }
//    return null
//}
//
//fun verificarJogoCompleto(): Boolean {
//    return cartasViradas == 8
//}








//class Jogo (private var linha: Int, private var coluna: Int){
//
//    var array: ""
//    private var imagemSelecionada: Int = ;
//
//    private var status: String ="Novo jogo"
//
//
//
//
//        fun onCreate() {
//            val boardSize = 4
//            val totalPairs = 8
//            val maxAttempts = 10
//            val board = createBoard(boardSize, totalPairs)
//            val scanner = Scanner(System.`in`)
//            var attempts = 0
//            var foundPairs = 0
//
//           println("Bem-vindo ao Jogo da Memória!")
//
//            while (attempts < maxAttempts && foundPairs < totalPairs) {
//                printBoard(board)
//                println("Tentativa ${attempts + 1}: Escolha duas coordenadas (linha coluna):")
//
//                val (linha1, coluna1) = readCoordinates(scanner, boardSize)
//                val (linha2, coluna2) = readCoordinates(scanner, boardSize)
//
//                if (linha1 == linha2 && coluna1 == coluna2) {
//                    println("Você escolheu a mesma posição duas vezes. Tente novamente.")
//                    continue
//                }
//
//                if (board[linha1][coluna1] == board[linha2][coluna2]) {
//                    println("Par encontrado!")
//                    board[linha1][coluna1] = ' '
//                    board[linha2][coluna2] = ' '
//                    foundPairs++
//                } else {
//                    println("Tente novamente.")
//                }
//
//                attempts++
//            }
//
//            if (foundPairs == totalPairs) {
//                println("Parabéns! Você encontrou todos os pares em $attempts tentativas.")
//            } else {
//                println("Você atingiu o limite de tentativas. O jogo acabou.")
//            }
//        }
//
//        fun createBoard(size: Int, totalPairs: Int): Array<Array<Char>> {
//            val characters = ('A'..'Z').toList()
//            val pairs = characters.subList(0, totalPairs)
//            val random = Random()
//            val shuffledPairs = (pairs + pairs).shuffled(random)
//
//            val board = Array(size) { Array(size) { ' ' } }
//
//            for (i in 0 until size) {
//                for (j in 0 until size) {
//                    board[i][j] = shuffledPairs[i * size + j]
//                }
//            }
//
//            return board
//        }
//
//        fun printBoard(board: Array<Array<Char>>) {
//            for (row in board) {
//                println(row.joinToString(" "))
//            }
//        }
//
//        fun readCoordinates(scanner: Scanner, boardSize: Int): Pair<Int, Int> {
//            var linha = -1
//            var coluna = -1
//
//            while (linha < 0 || linha >= boardSize || coluna < 0 || coluna >= boardSize) {
//                print("Informe a linha e a coluna: ")
//                val input = scanner.nextLine().trim().split(" ")
//
//                if (input.size != 2) {
//                   println("Entrada inválida. Use o formato 'linha coluna'.")
//                    continue
//                }
//
//                try {
//                    linha = input[0].toInt()
//                    coluna = input[1].toInt()
//                } catch (e: NumberFormatException) {
//                    println("Entrada inválida. Use números para linha e coluna.")
//                }
//            }
//
//            return Pair(linha, coluna)
//        }
//}