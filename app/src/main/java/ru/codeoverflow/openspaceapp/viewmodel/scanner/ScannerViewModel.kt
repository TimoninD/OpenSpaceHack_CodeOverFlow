package ru.codeoverflow.openspaceapp.viewmodel.scanner

import android.util.Log
import kotlinx.coroutines.launch
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.entity.dto.camera.toModel
import ru.codeoverflow.openspaceapp.model.interactor.ScannerInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel
import java.io.File

class ScannerViewModel : BaseViewModel() {
    private val interactor: ScannerInteractor by inject()

    fun postMeterImage(image: File) {
        coroutineScope.launch {
            try {
                val result = interactor.postMeterPhoto(image)
                Log.e("Result", result.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}