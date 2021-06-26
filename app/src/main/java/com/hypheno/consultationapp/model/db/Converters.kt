package com.hypheno.consultationapp.model.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hypheno.consultationapp.model.dataclass.ConsultationRequest
import com.hypheno.consultationapp.model.dataclass.Message
import java.util.*

class Converters {

    @TypeConverter
    fun fromConsultationToString(value: ConsultationRequest): String? {
        return value.doctor_name + "," + value.active.toString()
    }

    @TypeConverter
    fun toConsultation(value: String): ConsultationRequest {
        return ConsultationRequest(value.split(",")[1].toBoolean(), value.split(",")[0])
    }

    @TypeConverter
    fun fromMessageToString(value: List<Message>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMessage(value: String): List<Message> {
        val listType = object : TypeToken<List<Message>>() {

        }.type

        return Gson().fromJson<List<Message>>(value, listType)
    }
}