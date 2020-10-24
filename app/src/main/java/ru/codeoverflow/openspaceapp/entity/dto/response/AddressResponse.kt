package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType

data class AddressResponse(
    @SerializedName("buildingType") val buildingType: String,
    @SerializedName("address") val address: String,
    @SerializedName("meters") val meters: List<MeterResponse>,
    @SerializedName("totalPrice") val totalPrice: Float,
    @SerializedName("personalAccount") val personalAccount: String
)

fun AddressResponse.toModel() =
    AddressModel(
        buildingType = this.buildingType,
        address = this.address,
        listMeter = this.meters.map { it.toModel() },
        totalPrice = this.totalPrice,
        personalAccount = this.personalAccount,
        type = when (buildingType) {
            AddressType.APARTMENT.itemId -> AddressType.APARTMENT
            AddressType.HOUSE.itemId -> AddressType.HOUSE
            AddressType.VILLAGE.itemId -> AddressType.VILLAGE
            else -> AddressType.OFFICE
        }
    )