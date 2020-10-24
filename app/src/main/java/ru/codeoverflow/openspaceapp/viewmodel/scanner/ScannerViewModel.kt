package ru.codeoverflow.openspaceapp.viewmodel.scanner

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.ScannerInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class ScannerViewModel : BaseViewModel() {
    private val interactor: ScannerInteractor by inject()
}