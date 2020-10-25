package ru.codeoverflow.openspaceapp.entity.dto.request

import com.google.gson.annotations.SerializedName

data class EditMeterRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("addressId") val addressId: String,
    @SerializedName("meterId") val meterId: String,
    @SerializedName("meterType") val meterType: String,
    @SerializedName("price") val price: Int,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("isPaid") val isPaid: Boolean,
    @SerializedName("value") val value: String
)