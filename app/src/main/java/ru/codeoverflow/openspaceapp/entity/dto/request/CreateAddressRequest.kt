package ru.codeoverflow.openspaceapp.entity.dto.request

import com.google.gson.annotations.SerializedName

data class CreateAddressRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("address") val address: String,
    @SerializedName("personalAccount") val personalAccount: String,
    @SerializedName("buildingType") val buildingType: String
)