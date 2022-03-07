package com.example.recyclerViewTelegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.RenderProcessGoneDetail
import com.bumptech.glide.Glide
import com.example.recyclerwhatsapp.databinding.ActivityChatBinding
import com.example.recyclerwhatsapp.databinding.ActivityMainBinding

class ChatActivity : AppCompatActivity() {

    var esNuevo = false

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAndShowInfo()

        binding.ivReturnArrow.setOnClickListener {
            returnChats()
        }
    }

    fun getAndShowInfo(){
        val bundle = intent.extras

        //Cargamos la foto
        Glide.with(binding.ivphotoChat.context).load(bundle?.get("INTENT_Photo")).into(binding.ivphotoChat)

        //Cargamos el Nombre
        binding.tvNombreContactoChat.text = bundle?.get("INTENT_NombreContacto") as String?

        //Cargamos el estado de la conexión
        binding.tvEstadoConexion.text = bundle?.get("INTENT_ultVez") as String?

        //Cargamos el mensaje
        binding.tvchatInfo.text = bundle?.get("INTENT_Mensaje") as String?

        //Si es nuevo mensaje lo marcamos como leido
        val cantidad = bundle?.get("INTENT_cantidad") as Int
        if(cantidad > 0){
            esNuevo = true
        }


    }

    fun returnChats(){
        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)

    }
}