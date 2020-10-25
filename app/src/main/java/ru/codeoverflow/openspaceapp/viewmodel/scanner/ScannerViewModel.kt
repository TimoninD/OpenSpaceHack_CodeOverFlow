package ru.codeoverflow.openspaceapp.viewmodel.scanner

import kotlinx.coroutines.launch
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AddressInteractor
import ru.codeoverflow.openspaceapp.model.interactor.ScannerInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel
import java.io.File

class ScannerViewModel : BaseViewModel() {
    private val interactor: ScannerInteractor by inject()
    private val addressInteractor: AddressInteractor by inject()

    fun postMeterImage(image: File) {
        coroutineScope.launch {
            try {
                val result = interactor.postMeterPhoto(image)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}