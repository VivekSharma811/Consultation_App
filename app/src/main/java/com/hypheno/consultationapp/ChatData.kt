package com.hypheno.consultationapp

data class ChatData(
    val consultation_request: ConsultationRequest,
    val messages: List<Message>
)