package ru.codeoverflow.openspaceapp.viewmodel.address

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.entity.dto.request.CreateAddressRequest
import ru.codeoverflow.openspaceapp.entity.dto.response.toModel
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddressViewModel : BaseViewModel() {
    private val interactor: AddressInteractor by inject()

    private val prefs: Prefs by inject()

    val listAddress: MutableLiveData<List<AddressModel>> = MutableLiveData()

    init {
        getAllAddress()
    }

    private fun getAllAddress() {
        coroutineScope.launch {
            try {
                val result = interactor.getAllAddress()
                val list = result?.listAddress?.map { it.toModel() } ?: listOf()
                if (prefs.userId == null) {
                    prefs.userId = result?.userId
                }
                withContext(Dispatchers.Main) {
                    listAddress.postValue(list)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}