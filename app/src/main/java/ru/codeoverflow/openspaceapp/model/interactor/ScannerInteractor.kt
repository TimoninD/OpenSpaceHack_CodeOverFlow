package ru.codeoverflow.openspaceapp.model.interactor

import ru.codeoverflow.openspaceapp.extension.toFilePart
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi
import java.io.File

class ScannerInteractor(private val api: OpenSpaceApi) {

    fun postMeterPhoto(image: File) = api.postMeterPhoto(image.toFilePart())
}