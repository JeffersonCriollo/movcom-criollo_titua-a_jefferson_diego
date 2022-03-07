package com.example.recyclerViewTelegram

data class Chat(
    val nombre_usuario: String,
    val contenido_mensaje: String,
    val hora_mensaje: String,
    var nuevo_mensaje: Boolean,
    val photo_usuario: String,
    val ultima_vez: String,
    var cantidad: Int
)