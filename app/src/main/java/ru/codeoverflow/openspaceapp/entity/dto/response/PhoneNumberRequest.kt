package ru.codeoverflow.openspaceapp.entity.dto.response

import com.google.gson.annotations.SerializedName

data class PhoneNumberRequest(@SerializedName("phoneNumber") val phoneNumber: String)