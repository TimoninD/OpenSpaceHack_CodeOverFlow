package ru.codeoverflow.openspaceapp.ui.fragment

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.addaddress.AddAddressViewModel

class AddAddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_add_address

    private val vm: AddAddressViewModel by viewModel()
}