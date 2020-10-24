package ru.codeoverflow.openspaceapp.viewmodel.signin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AuthInteractor
import ru.codeoverflow.openspaceapp.util.SingleLiveData
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class SignInViewModel : BaseViewModel() {
    private val interactor: AuthInteractor by inject()

    val signInResult: SingleLiveData<String> = SingleLiveData()

    fun signIn(phone: String) {
        coroutineScope.launch {
            try {
                val result = interactor.signIn(phone)
                withContext(Dispatchers.Main) {
                    signInResult.postValue(result)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}