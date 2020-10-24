package ru.codeoverflow.openspaceapp.entity.core.detailaddress

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MeterModel(
    val type: DetailAddressType,
    val value: Int?,
    val price: Float?
) : Parcelable
