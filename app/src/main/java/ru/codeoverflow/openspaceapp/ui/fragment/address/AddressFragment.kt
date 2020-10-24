package ru.codeoverflow.openspaceapp.ui.fragment.address

import android.os.Bundle
import android.util.Log
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

    private val adapter: ListDelegationAdapter<List<BaseAddress>> by lazy {
        ListDelegationAdapter<List<BaseAddress>>(
            addressAdapterDelegate {
                findNavController().navigate(
                    AddressFragmentDirections.actionAddressFragmentToDetailAddressFragment(
                        it.id
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
            adapter.items = listOf(AddAddress()) + it
            adapter.notifyDataSetChanged()
        })
    }
}