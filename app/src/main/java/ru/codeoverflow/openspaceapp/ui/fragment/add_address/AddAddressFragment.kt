package ru.codeoverflow.openspaceapp.ui.fragment.add_address

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_address.*
import kotlinx.android.synthetic.main.layout_address_add_card.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.model.storage.Prefs
import ru.codeoverflow.openspaceapp.extension.hideKeyboard
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.viewmodel.addaddress.AddAddressViewModel

class AddAddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_add_address

    private val vm: AddAddressViewModel by viewModel()

    private val args: AddAddressFragmentArgs by navArgs()

    private val editTexts by lazy {
        listOf<EditText>(
            etCity, etStreet, etHouse, etApartment, etAccount
        )
    }

    private val typeIndicators by lazy {
        listOf<ImageView>(
            btnApartmentImg,
            btnHouseImg,
            btnVillageImg,
            btnOfficeImg
        )
    }

    private val activeTypeColorFilter by lazy {
        PorterDuffColorFilter(
            resources.getColor(android.R.color.transparent, context?.theme),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    private val inactiveTypeColorFilter by lazy {
        PorterDuffColorFilter(
            resources.getColor(R.color.second_text_color, context?.theme),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    private val prefs: Prefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs.isFirstAddShow = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTexts.forEach { editText ->
            editText.addTextChangedListener {
                if (!it.isNullOrBlank()) {
                    editText.error = null
                }
            }
        }

        tvAddAddress.text = getString(R.string.add_address_type_of_building)
        setBuildingTypeIndicatorsInitial()

        vm.addressType.observe(viewLifecycleOwner) { addressType ->
            if (addressType != null) {
                activateBuildingType(addressType)
            }
        }

        vm.addressCreateResult.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            findNavController().navigate(AddAddressFragmentDirections.actionAddAddressFragmentToAddressFragment())
        })

        vm.addressType.value = args.addressType

        btnApartment.setOnClickListener { vm.addressType.value = AddressType.APARTMENT }
        btnHouse.setOnClickListener { vm.addressType.value = AddressType.HOUSE }
        btnVillage.setOnClickListener { vm.addressType.value = AddressType.VILLAGE }
        btnOffice.setOnClickListener { vm.addressType.value = AddressType.OFFICE }


        btnNext.setOnClickListener {
            validateAndSendAddress()
        }
    }

    private fun setBuildingTypeIndicatorsInitial() {
        typeIndicators.forEach {
            it.colorFilter = inactiveTypeColorFilter
        }
    }

    private fun activateBuildingType(addressType: AddressType) {
        typeIndicators.forEach {
            if (isCurrentIndicator(it.id, addressType)) {
                it.colorFilter = activeTypeColorFilter
            } else {
                it.colorFilter = inactiveTypeColorFilter
            }
        }
    }

    private fun isCurrentIndicator(@IdRes id: Int, addressType: AddressType): Boolean {
        return when (addressType) {
            AddressType.APARTMENT -> id == R.id.btnApartmentImg
            AddressType.VILLAGE -> id == R.id.btnVillageImg
            AddressType.HOUSE -> id == R.id.btnHouseImg
            AddressType.OFFICE -> id == R.id.btnOfficeImg
            AddressType.NONE -> false
        }
    }

    private fun validateAndSendAddress() {
        editTexts.forEach { validateField(it) }

        if (editTexts.any { it.error != null }) {
            Toast.makeText(context, R.string.add_address_fill_all_fields, Toast.LENGTH_SHORT).show()
            return
        }

        if (vm.addressType.value == AddressType.NONE) {
            Toast.makeText(context, R.string.add_address_select_type, Toast.LENGTH_SHORT).show()
            return
        }

        val address = getString(
            R.string.address,
            etCity.text.toString(),
            etStreet.text.toString(),
            etHouse.text.toString(),
            etApartment.text.toString()
        )
        vm.createAddress(address = address, personalAccount = etAccount.text.toString())

    }

    private fun validateField(editText: EditText) {
        editText.apply {
            error = if (text.isNullOrBlank()) {
                getString(R.string.validation_error_empty)
            } else {
                null
            }
        }
    }
}