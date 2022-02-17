package com.example.infintestapp.app

import android.app.Application
import com.example.infintestapp.Preferences
import com.example.infintestapp.di.DaggerInfinAppComponent
import com.example.infintestapp.di.InfinAppComponent

class InfinTestApp : Application() {

    lateinit var appComponent: InfinAppComponent

    override fun onCreate() {
        super.onCreate()
        Preferences.init(this)

        appComponent = DaggerInfinAppComponent.create()

    }
}