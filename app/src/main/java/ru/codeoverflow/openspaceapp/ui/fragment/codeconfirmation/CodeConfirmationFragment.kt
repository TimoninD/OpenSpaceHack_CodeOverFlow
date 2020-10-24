package ru.codeoverflow.openspaceapp.ui.fragment.codeconfirmation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_code_confirmation.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.extension.hideKeyboard
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment

class CodeConfirmationFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_code_confirmation

    private val args: CodeConfirmationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvConfirmationDescription.text =
            getString(R.string.code_confirmation_description, args.phone)
        etPinCode.focus()
        etPinCode.setOnPinEnteredListener { pinCode ->
            print(pinCode)
            if (pinCode.toString() == "1234") {
                hideKeyboard()
                findNavController().navigate(
                    CodeConfirmationFragmentDirections.actionCodeConfirmationFragmentToHomeFragment()
                )
            }
        }
    }
}