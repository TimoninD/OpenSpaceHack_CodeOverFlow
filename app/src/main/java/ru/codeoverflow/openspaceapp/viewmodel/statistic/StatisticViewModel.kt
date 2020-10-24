package ru.codeoverflow.openspaceapp.viewmodel.statistic

import org.koin.core.inject
import ru.codeoverflow.openspaceapp.model.interactor.StatisticInteractor
import ru.codeoverflow.openspaceapp.viewmodel.BaseViewModel

class StatisticViewModel : BaseViewModel() {
    private val interactor: StatisticInteractor by inject()
}