package ru.codeoverflow.openspaceapp.entity.dto.response

import ru.codeoverflow.openspaceapp.entity.core.scanner.MeterScanModel

data class MeterScanResponse(val serialNumber: String?, val value: String?)

fun MeterScanResponse.toModel() = MeterScanModel(serialNumber = serialNumber, value = value)