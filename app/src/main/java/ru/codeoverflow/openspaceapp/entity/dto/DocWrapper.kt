package ru.codeoverflow.openspaceapp.entity.dto

import com.google.gson.annotations.SerializedName

data class DocWrapper<T : Any>(
    @SerializedName("doc") val doc: T?
)