package com.hypheno.consultationapp.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hypheno.consultationapp.model.dataclass.ChatData

@Dao
interface dao {

    @Insert
    fun insertAll(vararg chatData: ChatData)

    @Query("SELECT * FROM chat_data")
    fun getAllMessages(): LiveData<ChatData>

    @Query("DELETE FROM chat_data")
    fun deleteAll()
}