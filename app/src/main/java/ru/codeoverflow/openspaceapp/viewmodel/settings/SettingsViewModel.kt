package ru.codeoverflow.openspaceapp.viewmodel.settings

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.SettingsInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class SettingsViewModel : BaseViewModel() {

    private val interactor: SettingsInteractor by inject()
}