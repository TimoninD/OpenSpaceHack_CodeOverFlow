package ru.codeoverflow.openspaceapp.ui.fragment.address

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_address.*
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddAddress
import ru.codeoverflow.openspaceapp.entity.core.address.AddressItem
import ru.codeoverflow.openspaceapp.entity.core.address.AddressType
import ru.codeoverflow.openspaceapp.entity.core.address.BaseAddress
import ru.codeoverflow.openspaceapp.ui.list.address.addressAdapterDelegate
import ru.codeoverflow.openspaceapp.ui.list.address.addressAddAdapterDelegate

class AddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_address

    private val testList = listOf(
        AddressItem(AddressType.APARTMENT, "ул. Кубанская, д. 15а, кв. 36"),
        AddressItem(AddressType.HOUSE, "ул. Мира, д. 106")
    )

    private val adapter: ListDelegationAdapter<List<BaseAddress>> by lazy {
        ListDelegationAdapter<List<BaseAddress>>(
            addressAdapterDelegate {
                findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToDetailAddressFragment())
            },
            addressAddAdapterDelegate { }
        ).apply {
            items = listOf(AddAddress())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAddress.adapter = adapter
        adapter.items = adapter.items + testList
    }
}