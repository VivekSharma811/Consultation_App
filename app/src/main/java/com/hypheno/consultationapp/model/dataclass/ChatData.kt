package com.hypheno.consultationapp.model.dataclass

data class ChatData(
    val consultation_request: ConsultationRequest,
    val messages: List<Message>
)