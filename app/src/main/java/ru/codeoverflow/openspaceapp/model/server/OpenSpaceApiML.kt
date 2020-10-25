package ru.codeoverflow.openspaceapp.model.server

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ru.codeoverflow.openspaceapp.entity.dto.response.PostMeterResponse

interface OpenSpaceApiML {

    @POST("upload")
    @Multipart
    suspend fun postMeterPhoto(@Part file: MultipartBody.Part): PostMeterResponse
}