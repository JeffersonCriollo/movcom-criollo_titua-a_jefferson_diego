package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearVideojuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_modelo)

        val objMarca = intent.getParcelableExtra<BConsola>("consola")
        val btnGuardarModelo = findViewById<Button>(R.id.btn_crear_videojuego)
        btnGuardarModelo.setOnClickListener {

            if (objMarca != null) {

                val txtIdModelo = findViewById<EditText>(R.id.txt_id_videojuego)
                val txtNombreModelo = findViewById<EditText>(R.id.txt_nombre_videojuego)
                val txtDisponibleModelo = findViewById<Switch>(R.id.sw_disponible)
                val txtCostoModelo = findViewById<EditText>(R.id.txt_costo_videojuego)
                val nuevoModelo = hashMapOf<String, Any>(
                    "id" to txtIdModelo.text.toString().toInt(),
                    "nombre" to txtNombreModelo.text.toString(),
                    "disponibilidad" to txtDisponibleModelo.isChecked,
                    "costo" to txtCostoModelo.text.toString().toDouble(),
                    "idConsola" to objMarca.id
                )
                val db = Firebase.firestore
                var referencia =
                    db.collection("videojuego").document("${txtIdModelo.text}-${txtNombreModelo.text}")
                referencia.set(nuevoModelo).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.txt_nombre_videojuego).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    irActividadConMarca(Videojuego::class.java, objMarca)
                }


            }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConMarca(clase: Class<*>, consola: BConsola) {
        val intent = Intent(this, clase)
        intent.putExtra("consola", consola)
        startActivity(intent)
    }
}