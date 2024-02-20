package com.example.viacepapp.presenter

import android.app.Application
import com.example.viacepapp.di.moduleDeclaration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(moduleDeclaration)
        }
    }
}