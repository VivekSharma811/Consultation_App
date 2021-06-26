package com.hypheno.consultationapp.model.dataclass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_data")
data class ChatData(
    val consultation_request: ConsultationRequest,
    val messages: List<Message>
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}