package ru.codeoverflow.openspaceapp.ui.fragment.codeconfirmation

import android.location.Address
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_code_confirmation.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.extension.hideKeyboard
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.codeconfirm.CodeConfirmViewModel

private const val PIN_CODE_LENGTH = 4

class CodeConfirmationFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_code_confirmation

    private val args: CodeConfirmationFragmentArgs by navArgs()

    private val vm: CodeConfirmViewModel by viewModel()

    private val prefs: Prefs by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvConfirmationDescription.text =
            getString(R.string.code_confirmation_description, args.phone)
        etPinCode.focus()
        vm.codeConfirmResult.observe(viewLifecycleOwner, Observer {
            prefs.token = it
            hideKeyboard()
            findNavController().navigate(
                CodeConfirmationFragmentDirections.actionCodeConfirmationFragmentToHomeFragment()
            )
        })
        etPinCode.setOnPinEnteredListener { pinCode ->
            if (pinCode.length == PIN_CODE_LENGTH || pinCode.toString() == "1234") {
                vm.verify(args.phone, etPinCode.text.toString())
            }
        }
    }
}