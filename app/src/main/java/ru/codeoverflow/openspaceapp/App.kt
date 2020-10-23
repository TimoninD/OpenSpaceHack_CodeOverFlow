package ru.codeoverflow.openspaceapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.codeoverflow.openspaceapp.di.appModule
import ru.codeoverflow.openspaceapp.di.interactorModule
import ru.codeoverflow.openspaceapp.di.networkModule
import ru.codeoverflow.openspaceapp.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule, networkModule, viewModelModule, interactorModule
                )
            )
        }
    }
}