package ru.codeoverflow.openspaceapp.viewmodel.detailaddress

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class DetailsAddressViewModel : BaseViewModel() {
    private val interactor: AddressInteractor by inject()
}