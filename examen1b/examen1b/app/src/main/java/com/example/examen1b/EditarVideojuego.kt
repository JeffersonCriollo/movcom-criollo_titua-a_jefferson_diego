package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class EditarVideojuego : AppCompatActivity() {

    var idVideojuego = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_videojuego)
        idVideojuego = intent.getIntExtra("posicion",1)
        val txtActividad = findViewById<TextInputEditText>(R.id.txt_editar_actividad)
        val txtProGPP = findViewById<TextInputEditText>(R.id.txt_editar_promediogpp)
        val tvNombre = findViewById<TextView>(R.id.tv_editar_nombre)
        BDDMemoria.arrOVideojuego.forEachIndexed { index: Int, videojuego: OVideojuego ->
            if(index == idVideojuego){
                tvNombre.setText(videojuego.nombre.toString())
                txtActividad.setText(videojuego.Costo.toString())
                txtProGPP.setText(videojuego.Plataforma.toString())
            }
        }
        val btnSave = findViewById<Button>(R.id.btn_save_futbolista)
        btnSave.setOnClickListener {
            BDDMemoria.arrOVideojuego.forEachIndexed { index: Int, videojuego: OVideojuego ->
                if(index == idVideojuego){
                    videojuego.Costo = txtActividad.text.toString().trim().toDouble()
                    videojuego.Plataforma = txtProGPP.text.toString().trim().toString()
                }
            }
            val intentFutbolista = Intent(this, VideoJuegos::class.java)
            startActivity(intentFutbolista)
        }
    }

}
