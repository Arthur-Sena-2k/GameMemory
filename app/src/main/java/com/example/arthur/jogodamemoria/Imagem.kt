package com.example.arthur.jogodamemoria

import java.sql.RowId

data class Imagem(
    val id: Int,
    val imageResId: Int,
    var imgVirada: Boolean = false,
    var imgIgual: Boolean = false,
    var viewId: Int = -1
)
