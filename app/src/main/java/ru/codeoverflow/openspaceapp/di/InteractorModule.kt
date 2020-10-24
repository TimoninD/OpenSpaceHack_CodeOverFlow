package ru.codeoverflow.openspaceapp.di

import org.koin.dsl.module
import ru.codeoverflow.openspaceapp.model.interactor.*

val interactorModule = module {
    factory { AddressInteractor(get()) }
    factory { AuthInteractor(get()) }
    factory { ScannerInteractor() }
    factory { StatisticInteractor(get()) }

}