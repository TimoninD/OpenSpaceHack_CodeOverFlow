package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("address") val address: List<AddressResponse>
)