package ru.codeoverflow.openspaceapp.ui.fragment.address

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_address.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddAddress
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.entity.core.address.BaseAddress
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressType
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.ui.list.address.addressAdapterDelegate
import ru.codeoverflow.openspaceapp.ui.list.address.addressAddAdapterDelegate
import ru.codeoverflow.openspaceapp.viewmodel.address.AddressViewModel

class AddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_address

    private val vm: AddressViewModel by viewModel()

    /*private val testList = listOf(
        AddressModel(
            type = AddressType.APARTMENT,
            address = "ул. Даниила Тимонина, д. 106",
            listMeter = listOf(
                MeterModel(DetailAddressType.HOT_WATER, 15, 120.203f),
                MeterModel(DetailAddressType.COLD_WATER, 20, null),
                MeterModel(DetailAddressType.GAS, 5, null),
                MeterModel(DetailAddressType.LIGHTNING, null, null)
            ),
            totalPrice = 120.203f
        ),
        AddressModel(
            type = AddressType.HOUSE, address = "ул. Мира, д. 106", listMeter = listOf(
                MeterModel(DetailAddressType.HOT_WATER, 15, 120.203f),
                MeterModel(DetailAddressType.COLD_WATER, 20, null),
                MeterModel(DetailAddressType.GAS, 5, null),
                MeterModel(DetailAddressType.LIGHTNING, null, null)
            ), totalPrice = 120.203f
        )
    )*/

    private val adapter: ListDelegationAdapter<List<BaseAddress>> by lazy {
        ListDelegationAdapter<List<BaseAddress>>(
            addressAdapterDelegate {
                findNavController().navigate(
                    AddressFragmentDirections.actionAddressFragmentToDetailAddressFragment(
                        it
                    )
                )
            },
            addressAddAdapterDelegate { }
        ).apply {
            items = listOf(AddAddress())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAddress.adapter = adapter
        vm.listAddress.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
    }
}