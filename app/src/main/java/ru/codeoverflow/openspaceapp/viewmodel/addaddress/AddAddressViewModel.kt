package ru.codeoverflow.openspaceapp.viewmodel.addaddress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddAddressViewModel : BaseViewModel() {

    private val interactor: AddressInteractor by inject()

    val addressType: MutableLiveData<AddressType> = MutableLiveData()
}