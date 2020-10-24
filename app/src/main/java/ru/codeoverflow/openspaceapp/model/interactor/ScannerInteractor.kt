package ru.codeoverflow.openspaceapp.model.interactor

import org.koin.core.KoinComponent
import ru.codeoverflow.openspaceapp.entity.dto.request.EditMeterRequest
import ru.codeoverflow.openspaceapp.extension.toFilePart
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApiML
import java.io.File

class ScannerInteractor(private val apiMl: OpenSpaceApiML) :
    KoinComponent {

    suspend fun postMeterPhoto(image: File) = apiMl.postMeterPhoto(image.toFilePart())
}