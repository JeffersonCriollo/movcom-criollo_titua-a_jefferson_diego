package com.example.examen1b

class BDDMemoria {

    companion object {

        val arrOVideojuego = arrayListOf<OVideojuego>()
        val arrOConsola = arrayListOf<OConsola>()

        init {

            arrOVideojuego
                .add(
                    OVideojuego( 1, "Zelda", 60.00, "Nintendo" )
                )
            arrOVideojuego
                .add(
                    OVideojuego( 2, "Mario Odissey", 60.00, "Nintendo" )
                )
            arrOVideojuego
                .add(
                    OVideojuego( 3, "God of War", 60.00, "Play Station" )
                )

            arrOConsola
                .add(
                    OConsola( 1, "Play Station 3", false, 200.00, 1 )
                )

            arrOConsola
                .add(
                    OConsola( 2, "Xbox 360", false, 220.00, 2 )
                )

            arrOConsola
                .add(
                    OConsola( 3, "Nintendo Swicth", true, 299.99, 3 )
                )

        }

    }

}