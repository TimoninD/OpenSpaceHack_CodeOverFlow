package ru.codeoverflow.openspaceapp.viewmodel.detailaddress

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class DetailsAddressViewModel : BaseViewModel() {
    private val interactor: AddressInteractor by inject()

    val detailAddress: MutableLiveData<AddressModel> = MutableLiveData()

    fun getDetailAddress(addressId: String) {
        coroutineScope.launch {
            try {
                val result = interactor.getDetailAddress(addressId)
                detailAddress.postValue(result)

            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}