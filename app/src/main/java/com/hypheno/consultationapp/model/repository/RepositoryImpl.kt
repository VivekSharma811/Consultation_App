package com.hypheno.consultationapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hypheno.consultationapp.model.dataclass.ChatData
import com.hypheno.consultationapp.model.db.ChatDB
import com.hypheno.consultationapp.model.db.dao
import com.hypheno.consultationapp.model.network.datasource.NetworkDatasource
import com.hypheno.consultationapp.model.network.datasource.NetworkDatasourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val datasource: NetworkDatasource,
    private val dao: dao
) : Repository {

    init {
        datasource.messages.observeForever {
            storeMessageLocally(it)
        }
    }

    override suspend fun getMessages(): LiveData<ChatData> {
        return withContext(Dispatchers.IO) {
            fetchData()
            return@withContext dao.getAllMessages()
        }
    }

    private fun fetchData() {
        datasource.fetchMessages()
    }

    private fun storeMessageLocally(chatData: ChatData) {
        GlobalScope.launch {
            dao.deleteAll()
            dao.insertAll(chatData)
        }
    }
}