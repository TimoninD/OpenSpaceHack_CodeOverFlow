package ru.codeoverflow.openspaceapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.codeoverflow.openspaceapp.viewmodel.addaddress.AddAddressViewModel
import ru.codeoverflow.openspaceapp.viewmodel.address.AddressViewModel
import ru.codeoverflow.openspaceapp.viewmodel.detailaddress.DetailsAddressViewModel
import ru.codeoverflow.openspaceapp.viewmodel.scanner.ScannerViewModel
import ru.codeoverflow.openspaceapp.viewmodel.settings.SettingsViewModel
import ru.codeoverflow.openspaceapp.viewmodel.signin.SignInViewModel
import ru.codeoverflow.openspaceapp.viewmodel.statistic.StatisticViewModel

val viewModelModule = module {

    viewModel { AddAddressViewModel() }
    viewModel { AddressViewModel() }
    viewModel { DetailsAddressViewModel() }
    viewModel { ScannerViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { SignInViewModel() }
    viewModel { StatisticViewModel() }

}