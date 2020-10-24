package ru.codeoverflow.openspaceapp.entity.core.address

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel

@Parcelize
data class AddressModel(
    val type: AddressType,
    val address: String,
    val listMeter: List<MeterModel>,
    val totalPrice: Float,
    val buildingType: String,
    val personalAccount: String
) :
    BaseAddress(), Parcelable

open class BaseAddress

class AddAddress : BaseAddress()