package com.hypheno.consultationapp.model.network

import com.hypheno.consultationapp.model.dataclass.ChatData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "http://www.json-generator.com/"

interface ApiService {

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    @GET("api/json/get/bTTptqjGnC?indent=2")
    fun getData(): Single<ChatData>
}