package com.example.taskmate

import android.app.Application
import com.example.taskmate.data.AppContainer
import com.example.taskmate.data.DefaultAppContainer

class TaskApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer(this)
    }
}

