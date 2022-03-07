package com.example.recyclerViewTelegram.adapter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerViewTelegram.Chat
import com.example.recyclerwhatsapp.R

class ChatAdapter(val chatList:List<Chat>, private val onClickListener:(Chat)-> Unit):RecyclerView.Adapter<ChatViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatViewHolder(layoutInflater.inflate(R.layout.item_chat,parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = chatList[position]
        holder.render(item, onClickListener, position)
    }

    override fun getItemCount(): Int = chatList.size
}