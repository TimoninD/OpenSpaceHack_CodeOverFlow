package ru.codeoverflow.openspaceapp.entity.dto.request

import com.google.gson.annotations.SerializedName

data class PhoneVerifyRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("code") val code: String
)