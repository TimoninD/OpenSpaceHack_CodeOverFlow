package ru.codeoverflow.openspaceapp.model.server

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OpenSpaceApi {

    @POST("upload")
    @Multipart
    suspend fun postMeterPhoto(@Part image: MultipartBody.Part): Any
}