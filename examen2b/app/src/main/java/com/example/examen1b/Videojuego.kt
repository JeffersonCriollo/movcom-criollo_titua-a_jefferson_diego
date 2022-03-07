package com.example.examen1b

import com.example.motos.BVideojuego

import android.content.DialogInterface
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

class Videojuego : AppCompatActivity() {
    var posicionLista = 0
    var listaModelo = arrayListOf<BVideojuego>()
    var adaptador: ArrayAdapter<BVideojuego>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelo)

        val objMarca = intent.getParcelableExtra<BConsola>("consola")

        val db = Firebase.firestore
        var documentoModelo: (MutableList<DocumentSnapshot>)
        val modelos = db.collection("videojuego").orderBy("id")
        modelos.get().addOnSuccessListener {
            documentoModelo = it.documents
            documentoModelo.forEach { iteracion ->
                listaModelo.add(
                    BVideojuego(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("nombre").toString(),
                        iteracion.get("disponibilidad").toString().toBoolean(),
                        iteracion.get("costo").toString().toDouble(),
                        iteracion.get("idConsola").toString().toInt()
                    )
                )
                Log.i("videojuego", "${listaModelo}")
            }
            if (listaModelo.size > 0) {
                val listV_videojuegos = findViewById<ListView>(R.id.lv_videojuego)
                adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listaModelo
                )
                listV_videojuegos.adapter = adaptador
                registerForContextMenu(listV_videojuegos)
                adaptador!!.notifyDataSetChanged()
            }
        }

        val btnIrCrearModelo = findViewById<Button>(R.id.btn_ir_crear_videojuego)
        btnIrCrearModelo.setOnClickListener {
            if (objMarca != null) {
                irActividadConMarca(CrearVideojuego::class.java, objMarca)
            }
        }

    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
        Log.i("videojuego", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objModelo = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_modelo -> {
                if (objModelo != null) {
                    irActividadConModelo(EditarConsola::class.java, objModelo)
                    Log.i("videojuego", "${objModelo}")
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

    fun irActividadConModelo(clase: Class<*>, videojuego: BVideojuego) {
        val intent = Intent(this, clase)
        intent.putExtra("videojuego", videojuego)
        startActivity(intent)
    }
}


