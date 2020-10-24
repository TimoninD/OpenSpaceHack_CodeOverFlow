package ru.codeoverflow.openspaceapp.ui.fragment.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import setMask
import ru.codeoverflow.openspaceapp.viewmodel.signin.SignInViewModel

class SignInFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sign_in

    private val vm: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etPhone.setMask(
            getString(R.string.mask_phone),
            changeText = { maskFilled, _, _ ->
                btnNext.isEnabled = maskFilled
            }
        )
        btnNext.setOnClickListener {
            findNavController().navigate(
                SignInFragmentDirections.actionSignInFragmentToCodeConfirmationFragment(etPhone.text.toString())
            )
        }
    }
}