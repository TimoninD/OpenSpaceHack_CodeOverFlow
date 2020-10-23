package ru.codeoverflow.openspaceapp.entity.core.address

data class AddressItem(val type: AddressType, val address: String) : BaseAddress()

open class BaseAddress

class AddAddress : BaseAddress()