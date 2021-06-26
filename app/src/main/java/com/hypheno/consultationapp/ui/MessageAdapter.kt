package com.hypheno.consultationapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hypheno.consultationapp.R
import com.hypheno.consultationapp.databinding.MessageItemLeftBinding
import com.hypheno.consultationapp.databinding.MessageItemLeftMedicationBinding
import com.hypheno.consultationapp.databinding.MessageItemRightBinding
import com.hypheno.consultationapp.model.dataclass.Message
import kotlinx.android.synthetic.main.message_item_left.view.*

class MessageAdapter(val mContext: Context, val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    class MessageViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var messageLeftMedicationBinding: MessageItemLeftMedicationBinding? = null
        private var messageItemRightBinding: MessageItemRightBinding? = null
        private var messageItemLeftBinding: MessageItemLeftBinding? = null

        companion object {
            fun create(
                inflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ): MessageViewHolder {
                val binding =
                    DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)

                return MessageViewHolder(binding)
            }
        }

//        constructor(binding: MessageItemLeftMedicationBinding) : super(binding.getRoot()) {
//            messageLeftMedicationBinding = binding
//        }
//
//        constructor(binding: MessageItemLeftBinding) : super(binding.getRoot()) {
//            messageItemLeftBinding = binding
//        }
//
//        constructor(binding: MessageItemRightBinding) : super(binding.getRoot()) {
//            messageItemRightBinding = binding
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder.create(LayoutInflater.from(parent.context), parent, viewType)
//        return when (position) {
//            1 -> {
//                val binding = MessageItemRightBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                MessageViewHolder(binding)
//            }
//            2 -> {
//                val binding = MessageItemRightBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                MessageViewHolder(binding)
//            }
//            3 -> {
//                val binding = MessageItemLeftMedicationBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                MessageViewHolder(binding)
//            }
//            else -> {
//                val binding = MessageItemLeftBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                MessageViewHolder(binding)
//            }
//        }
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
                        var str = "Recommended Lab Tests \n"
                        for (test in list) {
                            str += test.name + "\n"
                        }
                        str += "Note: Labs can be ordered once the consultation is complete"

                        holder.itemView.tvTextMessage.text = str
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
                    R.layout.message_item_left
                } ?: run {
                    R.layout.message_item_left_medication
                }
            }
        }
    }
}