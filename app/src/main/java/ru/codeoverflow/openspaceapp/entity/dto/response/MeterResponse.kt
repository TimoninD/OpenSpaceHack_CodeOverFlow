package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressType
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel

data class MeterResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("meterType") val meterType: String,
    @SerializedName("value") val value: Float?,
    @SerializedName("price") val price: Float?,
    @SerializedName("isPaid") val isPaid: Boolean,
    @SerializedName("isActive") val isActive: Boolean
)

fun MeterResponse.toModel() = MeterModel(
    id = this.id,
    isActive = this.isActive,
    isPaid = this.isPaid,
    price = this.price,
    value = this.value,
    type = when (this.meterType) {
        DetailAddressType.COLD_WATER.itemId -> DetailAddressType.COLD_WATER
        DetailAddressType.HOT_WATER.itemId -> DetailAddressType.HOT_WATER
        DetailAddressType.GAS.itemId -> DetailAddressType.GAS
        else -> DetailAddressType.LIGHTNING
    }
)