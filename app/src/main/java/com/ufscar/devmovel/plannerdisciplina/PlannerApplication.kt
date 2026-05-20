package com.ufscar.devmovel.plannerdisciplina

import android.app.Application
import com.ufscar.devmovel.plannerdisciplina.data.AppContainer

class PlannerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}