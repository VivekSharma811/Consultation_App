package com.hypheno.consultationapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hypheno.consultationapp.R
import com.hypheno.consultationapp.model.dataclass.Message
import kotlinx.android.synthetic.main.message_item_left.view.*

class MessageAdapter(val mContext: Context, val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MessageViewHolder {
        return if(position == 1) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.message_item_right, parent, false)
            MessageViewHolder(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.message_item_left, parent, false)
            MessageViewHolder(view)
        }
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.itemView.tvTextMessage.text = messages[position].payload.toString()
    }

    override fun getItemViewType(position: Int): Int {
        return if(messages[position].user.type.equals("customer")) {
            1
        } else {
            0
        }
    }
}