package ru.codeoverflow.openspaceapp.viewmodel.addaddress

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddAddressViewModel : BaseViewModel() {

    private val interactor: AddressInteractor by inject()
}