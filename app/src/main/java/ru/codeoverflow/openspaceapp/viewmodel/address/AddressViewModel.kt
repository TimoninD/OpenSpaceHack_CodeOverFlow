package ru.codeoverflow.openspaceapp.viewmodel.address

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class AddressViewModel : BaseViewModel() {
    private val interactor: AddressInteractor by inject()

    val listAddress: MutableLiveData<List<AddressModel>> = MutableLiveData()

    init {
        getAllAddress()
    }

    fun getAllAddress() {
        coroutineScope.launch {
            try {
                val list = interactor.getAllAddress() ?: listOf()
                withContext(Dispatchers.Main) {
                    listAddress.postValue(list)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}