package ru.codeoverflow.openspaceapp.entity.dto.request

data class EditMeterRequest(
    val userId: String,
    val addressId: String,
    val meterId: String,
    val meterType: String,
    val price: Float,
    val isActive: Boolean,
    val isPaid: Boolean
)