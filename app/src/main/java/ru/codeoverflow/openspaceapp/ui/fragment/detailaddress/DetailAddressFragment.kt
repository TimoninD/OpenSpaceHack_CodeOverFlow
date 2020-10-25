package ru.codeoverflow.openspaceapp.ui.fragment.detailaddress

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_address.*
import kotlinx.android.synthetic.main.fragment_detail_address.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.extension.format
import ru.codeoverflow.openspaceapp.ui.MainActivity
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.ui.list.detailaddress.detailAddressAdapterDelegate
import ru.codeoverflow.openspaceapp.viewmodel.detailaddress.DetailsAddressViewModel

class DetailAddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_detail_address

    private val args: DetailAddressFragmentArgs by navArgs()

    private val vm: DetailsAddressViewModel by viewModel()

    private val adapter: ListDelegationAdapter<List<MeterModel>> by lazy {
        ListDelegationAdapter<List<MeterModel>>(
            detailAddressAdapterDelegate {
                val destination =
                    DetailAddressFragmentDirections.actionDetailAddressFragmentToScanner()
                destination.addressId = args.addressId
                destination.meter = it
                findNavController().navigate(destination)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (requireActivity() is MainActivity) {
            requireActivity().iconLogoBank.isVisible = false
        }
        vm.getDetailAddress(args.addressId)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        vm.detailAddress.observe(viewLifecycleOwner, Observer {
            with(it) {
                toolbar.title = address.substringAfter(',')
                tvTotal.text =
                    requireContext().getString(R.string.detail_address_total, totalPrice.format())
                rvMeter.adapter = adapter
                adapter.items = listMeter
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        if (requireActivity() is MainActivity) {
            requireActivity().iconLogoBank.isVisible = true
        }
        super.onDestroy()
    }

}