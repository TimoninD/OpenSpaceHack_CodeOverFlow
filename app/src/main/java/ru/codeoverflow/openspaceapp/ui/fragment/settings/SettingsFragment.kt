package ru.codeoverflow.openspaceapp.ui.fragment.settings

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.settings.SettingsViewModel

class SettingsFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_settings

    private val vm: SettingsViewModel by viewModel()
}