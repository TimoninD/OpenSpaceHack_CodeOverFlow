package ru.codeoverflow.openspaceapp.entity.core.detailaddress

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MeterModel(
    val id: String,
    val type: DetailAddressType,
    val value: String?,
    val price: Int?,
    val isActive: Boolean = false,
    val isPaid: Boolean = false
) : Parcelable
