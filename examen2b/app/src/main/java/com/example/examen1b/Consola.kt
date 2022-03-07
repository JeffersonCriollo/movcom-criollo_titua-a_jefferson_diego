package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Consola : AppCompatActivity() {
    var posicionLista = 0
    var listaConsola = arrayListOf<BConsola>()
    var adaptador: ArrayAdapter<BConsola>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marca)

        val btnIrCrearMarca = findViewById<Button>(R.id.btn_ir_crear_consola)
        btnIrCrearMarca.setOnClickListener { irActividad(CrearConsola::class.java) }

        val db = Firebase.firestore
        var documentoConsola: (MutableList<DocumentSnapshot>)
        val consolas = db.collection("consola").orderBy("id")
        consolas.get().addOnSuccessListener {
            documentoConsola = it.documents
            documentoConsola.forEach { iteracion ->
                listaConsola.add(
                    BConsola(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("nombre").toString(),
                        iteracion.get("disponibilidad").toString().toBoolean(),
                        iteracion.get("costo").toString().toDouble()
                    )
                )
            }
            if (listaConsola.size > 0) {
                val listV_consolas = findViewById<ListView>(R.id.lv_consola)
                adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaConsola)
                listV_consolas.adapter = adaptador
                registerForContextMenu(listV_consolas)
                adaptador!!.notifyDataSetChanged()
            }
        }.addOnFailureListener { }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objConsola = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_marca -> {
                if (objConsola != null) {
                    irActividadConMarca(EditarConsola::class.java, objConsola)
                }
                return true
            }
            R.id.mi_verModelo -> {
                if (objConsola != null) {
                    irActividadConMarca(Videojuego::class.java, objConsola)
                    Log.i("consola", "${objConsola}")
                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
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

    fun eliminarMarca(consola: BConsola) {
        listaConsola.removeIf { consolas -> (consolas == consola) }
        Log.i("eliminar", "${listaConsola}")
    }
}