package ru.codeoverflow.openspaceapp.viewmodel.signin

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AuthInteractor
import ru.codeoverflow.openspaceapp.util.SingleLiveData
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

enum class SignInResult {
    success, error
}


class SignInViewModel : BaseViewModel() {
    private val interactor: AuthInteractor by inject()

    val signInResult: SingleLiveData<SignInResult> = SingleLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun signIn(phone: String) {
        coroutineScope.launch {
            try {
                isLoading.postValue(true)
                val result = interactor.signIn(phone)
                withContext(Dispatchers.Main) {
                    signInResult.postValue(SignInResult.success)
                }
            } catch (t: Throwable) {
                signInResult.postValue(SignInResult.error)
                t.printStackTrace()
            }
            isLoading.postValue(false)
        }
    }
}