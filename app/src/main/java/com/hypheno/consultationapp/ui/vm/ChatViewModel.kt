package com.hypheno.consultationapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hypheno.consultationapp.model.dataclass.ChatData
import com.hypheno.consultationapp.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _messages : LiveData<ChatData> = MutableLiveData()
    val messages : LiveData<ChatData>
        get() = _messages

    suspend fun fetchMessages() {
        _messages = repository.getMessages()
    }
}