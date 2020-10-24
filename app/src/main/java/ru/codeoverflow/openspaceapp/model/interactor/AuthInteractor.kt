package ru.codeoverflow.openspaceapp.model.interactor

import ru.codeoverflow.openspaceapp.entity.dto.request.PhoneVerifyRequest
import ru.codeoverflow.openspaceapp.entity.dto.response.PhoneNumberRequest
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi

class AuthInteractor(private val api: OpenSpaceApi) {

    suspend fun signIn(phone: String) = api.signUp(PhoneNumberRequest((phone))).status

    suspend fun verify(phone: String, code: String) =
        api.verify(PhoneVerifyRequest(phone, code)).token
}