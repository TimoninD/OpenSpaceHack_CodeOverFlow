package ru.codeoverflow.openspaceapp.model.interactor

import ru.codeoverflow.openspaceapp.entity.dto.response.toModel
import ru.codeoverflow.openspaceapp.model.server.OpenSpaceApi

class AddressInteractor(private val api: OpenSpaceApi) {

    suspend fun getAllAddress() = api.getAllAddress().data?.doc?.listAddress?.map {
        it.toModel()
    }

}