package com.hypheno.consultationapp

data class Payload(
    val file_name: String,
    val file_url: String,
    val id: Int,
    val medications: List<Medication>,
    val recommended_tests: List<RecommendedTest>,
    val text: String,
    val unique: String
)