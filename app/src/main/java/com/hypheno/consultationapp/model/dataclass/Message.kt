package com.hypheno.consultationapp.model.dataclass

import com.hypheno.consultationapp.model.dataclass.Payload
import com.hypheno.consultationapp.model.dataclass.User

data class Message(
    val channel_id: String,
    val consultation_id: Int,
    val consultation_request_id: Int,
    val customer_id: Any,
    val deliveredMessage: Long,
    val intentName: String,
    val lastReadMessage: Long,
    val message_id: String,
    val payload: Payload,
    val status: String,
    val timestamp: Long,
    val transient: Boolean,
    val typing: Boolean,
    val uniqueId: Long,
    val user: User
)