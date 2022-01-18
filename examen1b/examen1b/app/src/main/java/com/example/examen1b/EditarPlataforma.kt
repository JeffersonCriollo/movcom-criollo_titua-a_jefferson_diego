package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class EditarPlataforma : AppCompatActivity() {

    var idConsola = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_consola)
        idConsola = intent.getIntExtra("posicion",1)
        val txtPenal = findViewById<TextInputEditText>(R.id.txt_editar_penal)
        val txtMinuto = findViewById<TextInputEditText>(R.id.txt_editar_minuto)
        val tvTipo = findViewById<TextView>(R.id.tv_editar_tipo)
        BDDMemoria.arrOConsola.forEachIndexed { index: Int, gol: OConsola ->
            if(index == idConsola){
                tvTipo.setText(gol.nombre.toString())
                txtPenal.setText(gol.costo.toString())
                txtMinuto.setText(gol.nueva.toString())
            }
        }
        val btnSave = findViewById<Button>(R.id.btn_save_gol)
        btnSave.setOnClickListener {
            BDDMemoria.arrOConsola.forEachIndexed { index: Int, gol: OConsola ->
                if(index == idConsola){
                    gol.nueva = txtPenal.text.toString().trim().toBoolean()
                    gol.costo = txtMinuto.text.toString().trim().toDouble()
                }
            }
            val intentGol = Intent(this, Plataformas::class.java)
            startActivity(intentGol)
        }
    }
}