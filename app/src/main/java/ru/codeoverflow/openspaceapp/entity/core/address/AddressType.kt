package ru.codeoverflow.openspaceapp.entity.core.address

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.codeoverflow.openspaceapp.R

enum class AddressType(
    @DrawableRes val imageId: Int,
    @StringRes val typeNameId: Int
) {
    APARTMENT(imageId = R.drawable.ic_apartment, typeNameId = R.string.apartment_type),
    DACHA(imageId = R.drawable.ic_dacha, typeNameId = R.string.dacha_type),
    HOUSE(imageId = R.drawable.ic_house, typeNameId = R.string.house_type),
    OFFICE(imageId = R.drawable.ic_office, typeNameId = R.string.office_type),

    NONE(imageId = 0, typeNameId = 0)
}