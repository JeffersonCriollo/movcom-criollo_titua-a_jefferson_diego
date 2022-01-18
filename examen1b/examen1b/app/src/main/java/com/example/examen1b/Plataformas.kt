package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class Plataformas : AppCompatActivity() {

    var itemSelected = 0
    var idvideojuego = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idvideojuego = intent.getIntExtra("posicion",1)
        setContentView(R.layout.activity_bconsolas)
        val listView = findViewById<ListView>( R.id.lv_goles )
        var arrConsolaTipo = arrayListOf<String>()
        BDDMemoria.arrOConsola.forEach { consola: OConsola ->
            if ( consola.id == idvideojuego )
                arrConsolaTipo.add(consola.nombre.toString())
        }
        val futAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            //BDDMemoria.arrOFutbolista
            arrConsolaTipo
        )
        listView.adapter = futAdapter
        futAdapter.notifyDataSetChanged()
        registerForContextMenu( listView )
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu( menu, v, menuInfo )
        var inflater = menuInflater
        inflater.inflate( R.menu.menu_consola, menu )
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemSelected = id
    }

    override fun onContextItemSelected( item: MenuItem): Boolean {
        return when( item.itemId ) {
            R.id.mg_editar -> {
                openActivityWithParameters( EditarPlataforma::class.java )
                return true
            }
            R.id.mg_eliminar -> {
                delete( itemSelected )
                return true
            }
            else -> return super.onContextItemSelected( item )
        }
    }

    fun openActivityWithParameters(
        clase: Class<*>
    ){
        val intentGol = Intent(this,clase)
        intentGol.putExtra( "posicion", itemSelected )
        startActivity( intentGol )
    }

    fun delete(
        itemSelected: Int
    ){
        val listViewGoles = findViewById<ListView>( R.id.lv_goles )
        BDDMemoria.arrOConsola.removeAt( itemSelected )
        var arrConsolaTipo = arrayListOf<String>()
        BDDMemoria.arrOConsola.forEach { consola: OConsola ->
            if ( consola.id == idvideojuego )
                arrConsolaTipo.add(consola.nombre.toString())
        }
        val futAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrConsolaTipo
        )
        listViewGoles.adapter = futAdapter
        futAdapter.notifyDataSetChanged()
    }

}