package ru.codeoverflow.openspaceapp.model.server

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part
import ru.codeoverflow.openspaceapp.entity.dto.camera.MeterScanResponse

interface OpenSpaceApi {

    @Multipart
    fun postMeterPhoto(@Part image: MultipartBody.Part): MeterScanResponse
}