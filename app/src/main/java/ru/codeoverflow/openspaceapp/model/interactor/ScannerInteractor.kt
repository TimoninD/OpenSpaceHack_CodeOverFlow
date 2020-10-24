package ru.codeoverflow.openspaceapp.model.interactor

import org.koin.core.KoinComponent
import ru.codeoverflow.openspaceapp.extension.toFilePart
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApiML
import java.io.File

class ScannerInteractor(private val api: OpenSpaceApiML) : KoinComponent {

    suspend fun postMeterPhoto(image: File) = api.postMeterPhoto(image.toFilePart())
}