package ru.codeoverflow.openspaceapp.ui.fragment.signin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.signin.SignInViewModel

class SignInFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sign_in

    private val vm: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnTest.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
        }

    }
}