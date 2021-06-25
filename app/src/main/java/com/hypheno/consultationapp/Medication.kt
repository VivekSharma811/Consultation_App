package com.hypheno.consultationapp

data class Medication(
    val dose_quantity: Int,
    val dose_unit: String,
    val duration: Int,
    val duration_unit: String,
    val frequency: String,
    val instructions: Any,
    val name: String,
    val number_of_days: Int,
    val number_of_times: String,
    val route: Any
)