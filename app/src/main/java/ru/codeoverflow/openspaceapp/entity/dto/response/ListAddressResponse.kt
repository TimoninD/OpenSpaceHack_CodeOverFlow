package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName

data class ListAddressResponse(
    @SerializedName("id") val userId: String,
    @SerializedName("addresses") val listAddress: List<AddressResponse>
)