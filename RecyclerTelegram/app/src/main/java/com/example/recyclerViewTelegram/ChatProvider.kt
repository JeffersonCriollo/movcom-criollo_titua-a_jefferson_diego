package com.example.recyclerViewTelegram

class ChatProvider {
    companion object{
        val chatList = listOf(
            Chat(
                "Byron Criollo",
                "En los textos expositivos, el párrafo de introducción registra el tema del texto, mientras que el párrafo de cierre concluye con él. La estructura del párrafo de introducción se divide en tres partes, a saber, la contextualización, la enunciación del tema y la anticipación de los subtemas",
                "19:07",
                true,
                "https://www.movilzona.es/app/uploads-movilzona.es/2019/05/Foto-de-Perfil-en-WhatsApp.jpg",
                "últ. vez a las 10:08",
                2
            ),
            Chat(
                "Jefferson Criollo",
                "Los párrafos de introducción son aquellos párrafos en los que se enuncia el tema principal del texto y cómo se abordará el tema",
                "19:00",
                true,
                "https://www.webespacio.com/wp-content/uploads/2010/12/perfil-facebook.jpg",
                "en línea",
                1
            ),
            Chat(
                "Nombre 3",
                "ha decidido rediseñar cómo se muestran los perfiles de todos los usuarios.",
                "19:05",
                false,
                "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
                "en línea",
                0
            )
        )
    }
}