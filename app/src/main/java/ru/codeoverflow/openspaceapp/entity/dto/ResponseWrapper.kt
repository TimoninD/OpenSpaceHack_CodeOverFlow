package ru.codeoverflow.openspaceapp.entity.dto

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T : Any>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: T?,
    @SerializedName("token") val token: String?
)