package com.hypheno.consultationapp.di.module

import android.content.Context
import androidx.room.Room
import com.hypheno.consultationapp.model.db.ChatDB
import com.hypheno.consultationapp.model.db.dao
import com.hypheno.consultationapp.model.network.datasource.NetworkDatasource
import com.hypheno.consultationapp.model.network.datasource.NetworkDatasourceImpl
import com.hypheno.consultationapp.model.repository.Repository
import com.hypheno.consultationapp.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ChatModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) : ChatDB {
        return Room.databaseBuilder(
            context,
            ChatDB::class.java,
            "chatdb"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: ChatDB) : dao {
        return db.dao()
    }

    @Singleton
    @Provides
    fun provideDataSource() : NetworkDatasource {
        return NetworkDatasourceImpl()
    }

    @Singleton
    @Provides
    fun provideRepository(datasource: NetworkDatasource, dao: dao) : Repository {
        return RepositoryImpl(datasource, dao)
    }

}