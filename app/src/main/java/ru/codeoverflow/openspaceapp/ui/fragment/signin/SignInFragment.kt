package ru.codeoverflow.openspaceapp.ui.fragment.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.signin.SignInResult
import ru.codeoverflow.openspaceapp.viewmodel.signin.SignInViewModel
import setMask

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

        vm.signInResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                SignInResult.success -> findNavController().navigate(
                    SignInFragmentDirections.actionSignInFragmentToCodeConfirmationFragment(etPhone.text.toString())
                )
                SignInResult.error -> Toast.makeText(context, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
            }
        })

        vm.isLoading.observe(viewLifecycleOwner, Observer {
            pbLoading.isVisible = it
        })

        btnNext.setOnClickListener {
            vm.signIn(etPhone.text.toString())
        }
    }
}