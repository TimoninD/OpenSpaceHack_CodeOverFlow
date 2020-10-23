package ru.codeoverflow.openspaceapp.ui.fragment.signin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import setMask

class SignInFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etPhone.setMask(
            getString(R.string.mask_phone),
            changeText = { maskFilled, extractedValue, formattedValue ->
                btnNext.isEnabled = maskFilled
            }
        )
    }
}