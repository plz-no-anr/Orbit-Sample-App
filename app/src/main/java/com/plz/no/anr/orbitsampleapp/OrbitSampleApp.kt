package com.plz.no.anr.orbitsampleapp

import android.app.Application
import com.plz.no.anr.orbitsampleapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OrbitSampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OrbitSampleApp)
            modules(appModule)
        }
    }
}