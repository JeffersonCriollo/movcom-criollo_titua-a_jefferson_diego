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

class VideoJuegos : AppCompatActivity() {

    var itemSelected = 0

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_videojuego )
        val listView = findViewById<ListView>( R.id.lv_futbolistas )
        var arrFutbolistaNombre = arrayListOf<String>()
        BDDMemoria.arrOVideojuego.forEach { videojuego: OVideojuego ->
            arrFutbolistaNombre.add(videojuego.nombre.toString())
        }
        val futAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            //BDDMemoria.arrOFutbolista
            arrFutbolistaNombre
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
        inflater.inflate( R.menu.menu_videojuego, menu )
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemSelected = id
    }

    override fun onContextItemSelected( item: MenuItem ): Boolean {
        return when( item.itemId ) {
            R.id.mf_editar -> {
                openActivityWithParameters( EditarVideojuego::class.java )
                return true
            }
            R.id.mf_eliminar -> {
                delete( itemSelected )
                return true
            }
            R.id.mf_ver -> {
                openActivityWithParameters( Plataformas::class.java )
                return true
            }
            else -> return super.onContextItemSelected( item )
        }
    }

    fun openActivityWithParameters(
        clase: Class<*>
    ){
        val intentFutbolista = Intent(this,clase)
        intentFutbolista.putExtra( "posicion", itemSelected + 1)
        startActivity( intentFutbolista )
    }

    fun delete(
        itemSelected: Int
    ){
        val listViewFutbolistas = findViewById<ListView>( R.id.lv_futbolistas )
        BDDMemoria.arrOVideojuego.removeAt( itemSelected )
        var arrFutbolistaNombre = arrayListOf<String>()
        BDDMemoria.arrOVideojuego.forEach { futbolista: OVideojuego ->
            arrFutbolistaNombre.add(futbolista.nombre.toString())
        }
        val futAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrFutbolistaNombre
        )
        listViewFutbolistas.adapter = futAdapter
        futAdapter.notifyDataSetChanged()
    }

}