package ru.codeoverflow.openspaceapp.entity.core.detailaddress

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.codeoverflow.openspaceapp.R

enum class DetailAddressType(
    @DrawableRes val imageId: Int,
    @StringRes val typeNameId: Int,
    @DrawableRes val littleImageId: Int,
    val itemId: String
) {
    COLD_WATER(
        imageId = R.drawable.ic_water_cold,
        typeNameId = R.string.cold_water_type,
        littleImageId = R.drawable.ic_little_water,
        itemId = "coldWater"
    ),
    HOT_WATER(
        imageId = R.drawable.ic_water_hot,
        typeNameId = R.string.hot_water_type,
        littleImageId = R.drawable.ic_little_water,
        itemId = "hotWater"
    ),
    GAS(
        imageId = R.drawable.ic_gas,
        typeNameId = R.string.gas_type,
        littleImageId = R.drawable.ic_little_gas,
        itemId = "gas"
    ),
    LIGHTNING(
        imageId = R.drawable.ic_lightning,
        typeNameId = R.string.lightning_type,
        littleImageId = R.drawable.ic_little_lightning,
        itemId = "electicity"
    )

}