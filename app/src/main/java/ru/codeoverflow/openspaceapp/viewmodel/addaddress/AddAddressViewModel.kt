package ru.codeoverflow.openspaceapp.viewmodel.addaddress

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.entity.dto.request.CreateAddressRequest
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddAddressViewModel : BaseViewModel() {

    private val interactor: AddressInteractor by inject()

    val addressType: MutableLiveData<AddressType> = MutableLiveData()

    val addressCreateResult: MutableLiveData<String> = MutableLiveData()

    private val prefs: Prefs by inject()

    fun createAddress(address: String, personalAccount: String) {
        coroutineScope.launch {
            try {
                prefs.userId?.let {
                    val result = interactor.createAddress(
                        CreateAddressRequest(
                            userId = it,
                            address = address,
                            personalAccount = personalAccount,
                            buildingType = addressType.value?.itemId.orEmpty()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        addressCreateResult.postValue(result)
                    }
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}