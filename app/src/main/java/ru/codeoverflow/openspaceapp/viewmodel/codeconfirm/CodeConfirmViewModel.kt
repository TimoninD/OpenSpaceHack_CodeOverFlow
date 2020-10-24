package ru.codeoverflow.openspaceapp.viewmodel.codeconfirm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.AuthInteractor
import ru.codeoverflow.openspaceapp.util.SingleLiveData
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class CodeConfirmViewModel : BaseViewModel() {
    private val interactor: AuthInteractor by inject()

    val codeConfirmResult: SingleLiveData<String> = SingleLiveData()

    fun verify(phoneString: String, code: String) {
        coroutineScope.launch {
            try {
                val result = interactor.verify(phoneString, code)
                withContext(Dispatchers.Main) {
                    codeConfirmResult.postValue(result)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }
}