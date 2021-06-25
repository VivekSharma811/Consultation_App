package com.hypheno.consultationapp

data class ConsultationRequest(
    val active: Boolean,
    val app_version: Any,
    val chat_incomplete_reasons: List<Any>,
    val chief_complaint: String,
    val consent: Boolean,
    val created_at: String,
    val customer_id: Int,
    val doctor_id: Int,
    val doctor_name: String,
    val duration: Any,
    val free: Boolean,
    val id: Int,
    val initiator_id: Int,
    val request_origin: String,
    val request_status: String,
    val request_type: String,
    val updated_at: String
)