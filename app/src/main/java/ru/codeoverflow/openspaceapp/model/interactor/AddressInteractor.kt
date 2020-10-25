package ru.codeoverflow.openspaceapp.model.interactor

import ru.codeoverflow.openspaceapp.entity.dto.request.CreateAddressRequest
import ru.codeoverflow.openspaceapp.entity.dto.request.EditMeterRequest
import ru.codeoverflow.openspaceapp.entity.dto.response.toModel
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi

class AddressInteractor(private val api: OpenSpaceApi) {

    suspend fun getAllAddress() = api.getAllAddress().data?.doc

    suspend fun getDetailAddress(addressId: String) =
        api.getAllAddress().data?.doc?.listAddress?.find { it.id == addressId }?.toModel()

    suspend fun createAddress(createAddressRequest: CreateAddressRequest) =
        api.createAddress(createAddressRequest).status

    suspend fun editMeter(request: EditMeterRequest) = api.editMeter(request).status

}