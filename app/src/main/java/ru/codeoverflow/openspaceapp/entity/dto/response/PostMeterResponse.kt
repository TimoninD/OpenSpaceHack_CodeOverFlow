package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName
import ru.codeoverflow.openspaceapp.entity.core.scanner.MeterScanModel

data class PostMeterResponse(
    @SerializedName("serialNumber") val serialNumber: String?,
    @SerializedName("value") val value: String?
)

fun PostMeterResponse.toModel() = MeterScanModel(
    serialNumber = this.serialNumber, value = this.value
)