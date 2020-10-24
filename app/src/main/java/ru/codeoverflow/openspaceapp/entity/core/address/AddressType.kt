package ru.codeoverflow.openspaceapp.entity.core.address

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.codeoverflow.openspaceapp.R

enum class AddressType(
    @DrawableRes val imageId: Int,
    @StringRes val typeNameId: Int,
    val itemId: String
) {
    APARTMENT(
        imageId = R.drawable.ic_apartment,
        typeNameId = R.string.apartment_type,
        itemId = "apartment"
    ),
    VILLAGE(imageId = R.drawable.ic_dacha, typeNameId = R.string.dacha_type, itemId = "village"),
    HOUSE(imageId = R.drawable.ic_house, typeNameId = R.string.house_type, itemId = "house"),
    OFFICE(imageId = R.drawable.ic_office, typeNameId = R.string.office_type, itemId = "office")

}