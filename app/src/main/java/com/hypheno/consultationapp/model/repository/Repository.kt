package com.hypheno.consultationapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hypheno.consultationapp.model.dataclass.ChatData

interface Repository {

    suspend fun getMessages() : LiveData<ChatData>
}