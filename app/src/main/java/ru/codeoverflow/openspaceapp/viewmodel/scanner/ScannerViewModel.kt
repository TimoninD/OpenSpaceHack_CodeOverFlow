package ru.codeoverflow.openspaceapp.viewmodel.scanner

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.entity.core.scanner.MeterScanModel
import ru.codeoverflow.openspaceapp.entity.dto.request.EditMeterRequest
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.model.interactor.ScannerInteractor
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel
import java.io.File

private const val MOCK_PRICE = 120

class ScannerViewModel : BaseViewModel() {
    private val interactor: ScannerInteractor by inject()
    private val addressInteractor: AddressInteractor by inject()
    private val prefs: Prefs by inject()

    val meterResult: MutableLiveData<MeterScanModel> = MutableLiveData()
    val meterEditResult: MutableLiveData<String> = MutableLiveData()

    fun postMeterImage(image: File) {
        coroutineScope.launch {
            try {
                val result =
                    MeterScanModel("123321a123", "123321123")//interactor.postMeterPhoto(image)
                withContext(Dispatchers.Main) {
                    meterResult.postValue(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun editMeter(addressId: String, meterModel: MeterModel) {
        coroutineScope.launch {
            try {
                val result =
                    addressInteractor.editMeter(
                        EditMeterRequest(
                            userId = prefs.userId.orEmpty(),
                            addressId = addressId,
                            meterId = meterModel.id,
                            meterType = meterModel.type.itemId,
                            isPaid = meterModel.isPaid,
                            isActive = meterModel.isActive,
                            price = MOCK_PRICE,
                            value = meterModel.value.orEmpty()
                        )
                    )
                meterEditResult.postValue(result)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}