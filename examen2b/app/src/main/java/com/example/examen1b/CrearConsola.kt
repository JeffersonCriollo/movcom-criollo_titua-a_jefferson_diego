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


class CrearConsola : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)
        val btnGuardarConsola = findViewById<Button>(R.id.btn_crear_consola)

        btnGuardarConsola.setOnClickListener {
            val txtIdMarca = findViewById<EditText>(R.id.txt_id_consola)
            val txtNombreMarca = findViewById<EditText>(R.id.txt_nombre_consola)
            val txtEsCompetenciaMarca = findViewById<Switch>(R.id.sw_competencia)
            val txtPromedioVentasMarca = findViewById<EditText>(R.id.txt_promedio_consola)
            val nuevaMarca = hashMapOf<String, Any>(
                "id" to txtIdMarca.text.toString().toInt(),
                "nombre" to txtNombreMarca.text.toString(),
                "disponibilidad" to txtEsCompetenciaMarca.isChecked,
                "costo" to txtPromedioVentasMarca.text.toString().toDouble()
            )
            val db = Firebase.firestore
            val referencia = db.collection("consola").document("${txtIdMarca.text}-${txtNombreMarca.text}")
            referencia
                .set(nuevaMarca)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.txt_nombre_consola).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this,
                            Consola::class.java
                        )
                    )
                }
        }
    }
}