package ru.codeoverflow.openspaceapp.extension

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toFilePart(name: String = "photo"): MultipartBody.Part {
    val fileReqBody = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, this.name, fileReqBody)
}