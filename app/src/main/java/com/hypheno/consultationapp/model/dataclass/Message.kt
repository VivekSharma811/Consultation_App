package com.hypheno.consultationapp.model.dataclass

import com.hypheno.consultationapp.model.dataclass.Payload
import com.hypheno.consultationapp.model.dataclass.User

data class Message(
    val payload: Payload,
    val user: User
)