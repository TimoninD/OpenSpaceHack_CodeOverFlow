package ru.codeoverflow.openspaceapp.model.interactor

import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.extension.toFilePart
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi
import java.io.File

class ScannerInteractor:KoinComponent {

    private val api: OpenSpaceApi by inject()

    suspend fun postMeterPhoto(image: File) = api.postMeterPhoto(image.toFilePart())
}