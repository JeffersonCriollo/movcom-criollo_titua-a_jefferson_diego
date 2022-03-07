package com.example.recyclerViewTelegram.adapter

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerViewTelegram.Chat
import com.example.recyclerwhatsapp.R
import com.example.recyclerwhatsapp.databinding.ItemChatBinding
import de.hdodenhof.circleimageview.CircleImageView

class ChatViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val binding = ItemChatBinding.bind(view)

    fun render(chatModel: Chat, onClickListener:(Chat)-> Unit, position: Int){

        //Cargamos la Foto
        Glide.with(binding.ivphoto.context).load(chatModel.photo_usuario).into(binding.ivphoto)

        //Cargamos el nombre
        binding.tvNombreContacto.text = chatModel.nombre_usuario

        //Cargamos el mensaje
        if(chatModel.contenido_mensaje.length >= 57){
            var msg: String = ""
            for (i in 0..51){
                msg+=chatModel.contenido_mensaje[i]
            }
            msg+="..."
            binding.tvMensaje.text = msg
        }else{
            binding.tvMensaje.text = chatModel.contenido_mensaje
        }

        //Cargamos la hora del mensaje
        binding.tvHora.text = chatModel.hora_mensaje

        if(chatModel.nuevo_mensaje==true){
            binding.tvCantidad.visibility = View.VISIBLE
            binding.tvCantidad.text = chatModel.cantidad.toString()
            binding.ivNewMessage.visibility = View.VISIBLE
        }


        itemView.setOnClickListener { onClickListener(chatModel)}

    }

}