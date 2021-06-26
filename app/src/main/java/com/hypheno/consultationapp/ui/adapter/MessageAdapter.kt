package com.hypheno.consultationapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hypheno.consultationapp.R
import com.hypheno.consultationapp.databinding.MessageItemLeftMedicationBinding
import com.hypheno.consultationapp.databinding.MessageLeftTestBinding
import com.hypheno.consultationapp.model.dataclass.Message
import kotlinx.android.synthetic.main.message_item_left.view.*

class MessageAdapter(val mContext: Context, val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    class MessageViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(
                inflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ): MessageViewHolder {
                val binding =
                    DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)

                return MessageViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder.create(
            LayoutInflater.from(parent.context),
            parent,
            viewType
        )
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        messages[position].let {
            if (it.user.type.equals("customer")) {
                it.payload.text?.let {
                    holder.itemView.tvTextMessage.text = messages[position].payload.text
                } ?: run {
                    holder.itemView.tvTextMessage.text = "You have shared a document"
                }
            } else {
                it.payload.text?.let {
                    holder.itemView.tvTextMessage.text = messages[position].payload.text
                } ?: run {
                    it.payload.recommended_tests?.let { list ->
                        (holder.binding as MessageLeftTestBinding).payload = it.payload
                    } ?: run {
                        (holder.binding as MessageItemLeftMedicationBinding).medicine =
                            it.payload.medications?.get(0)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].user.type.equals("customer")) {
            messages[position].payload.text?.let {
                R.layout.message_item_right
            } ?: run {
                R.layout.message_item_right
            }
        } else {
            messages[position].payload.text?.let {
                R.layout.message_item_left
            } ?: run {
                messages[position].payload.recommended_tests?.let {
                    R.layout.message_left_test
                } ?: run {
                    R.layout.message_item_left_medication
                }
            }
        }
    }
}