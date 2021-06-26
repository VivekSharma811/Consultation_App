package com.hypheno.consultationapp.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConsultationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}