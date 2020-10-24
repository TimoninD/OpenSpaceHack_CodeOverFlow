package ru.codeoverflow.openspaceapp.viewmodel.signin

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AuthInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class SignInViewModel : BaseViewModel() {
    private val interactor: AuthInteractor by inject()
}