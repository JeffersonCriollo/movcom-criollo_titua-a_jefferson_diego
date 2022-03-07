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


class EditarConsola : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        val objMarca = intent.getParcelableExtra<BConsola>("consola")

        val id = objMarca!!.id.toString()
        val nombre = objMarca!!.nombre.toString()
        val competencia = objMarca!!.disponibilidad.toString()
        val costo = objMarca!!.Costo.toString()

        findViewById<EditText>(R.id.txt_id_consola_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_consola_editar).setText(nombre)
        findViewById<Switch>(R.id.sw_competencia_editar).setChecked(competencia.toBoolean())
        findViewById<EditText>(R.id.txt_promedio_consola_editar).setText(costo)

        val botonEditarMarca = findViewById<Button>(R.id.btn_crear_consola_editar)
        botonEditarMarca.setOnClickListener {
            val nuevaConsola = hashMapOf<String, Any>(
                "id" to findViewById<EditText>(R.id.txt_id_consola_editar).text.toString().toInt(),
                "nombre" to findViewById<EditText>(R.id.txt_nombre_consola_editar).text.toString(),
                "disponibilidad" to findViewById<Switch>(R.id.sw_competencia_editar).isChecked,
                "costo" to findViewById<EditText>(R.id.txt_promedio_consola_editar).text.toString()
                    .toDouble()
            )
            val db = Firebase.firestore
            val referencia =
                db.collection("consola").document("${id}-${nombre}")
            referencia.update(nuevaConsola)
                .addOnSuccessListener {
                    Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
                    irActividad(Consola::class.java)
                }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}