package ru.codeoverflow.openspaceapp.model.server

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.codeoverflow.openspaceapp.entity.dto.DocWrapper
import ru.codeoverflow.openspaceapp.entity.dto.ResponseWrapper
import ru.codeoverflow.openspaceapp.entity.dto.request.CreateAddressRequest
import ru.codeoverflow.openspaceapp.entity.dto.request.EditMeterRequest
import ru.codeoverflow.openspaceapp.entity.dto.request.PhoneVerifyRequest
import ru.codeoverflow.openspaceapp.entity.dto.response.ListAddressResponse
import ru.codeoverflow.openspaceapp.entity.dto.response.PhoneNumberRequest

interface OpenSpaceApi {

    @POST("users/signup")
    suspend fun signUp(@Body body: PhoneNumberRequest): ResponseWrapper<Unit>

    @POST("users/verify")
    suspend fun verify(@Body body: PhoneVerifyRequest): ResponseWrapper<Unit>

    @POST("addresses")
    suspend fun createAddress(@Body body: CreateAddressRequest): ResponseWrapper<Unit>

    //@POST("")

    @POST("meters")
    suspend fun editMeter(@Body body: EditMeterRequest): ResponseWrapper<Unit>

    @GET("users/me")
    suspend fun getAllAddress(): ResponseWrapper<DocWrapper<ListAddressResponse>>
}