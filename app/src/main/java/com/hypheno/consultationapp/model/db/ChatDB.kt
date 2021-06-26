package com.hypheno.consultationapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hypheno.consultationapp.model.dataclass.ChatData

@Database(
    entities = arrayOf(ChatData::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChatDB : RoomDatabase() {

    abstract fun dao(): dao

//    companion object {
//        @Volatile
//        private var instance: ChatDB? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            ChatDB::class.java,
//            "chatdb"
//        ).build()
//    }
}