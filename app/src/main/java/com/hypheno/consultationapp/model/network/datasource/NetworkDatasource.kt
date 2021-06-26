package com.hypheno.consultationapp.model.network.datasource

import androidx.lifecycle.LiveData
import com.hypheno.consultationapp.model.dataclass.ChatData

interface NetworkDatasource {

    val messages : LiveData<ChatData>

    fun fetchMessages()
}