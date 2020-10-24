package ru.codeoverflow.openspaceapp.viewmodel.address

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddressViewModel : BaseViewModel() {
    private val interactor: AddressInteractor by inject()
}