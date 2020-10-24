package ru.codeoverflow.openspaceapp.entity.dto

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T : Any>(
    @SerializedName("data") val data: T?
)