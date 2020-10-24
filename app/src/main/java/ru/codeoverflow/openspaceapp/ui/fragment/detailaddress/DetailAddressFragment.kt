package ru.codeoverflow.openspaceapp.ui.fragment.detailaddress

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_detail_address.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.extension.format
import ru.codeoverflow.openspaceapp.extension.setTitle
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.ui.list.detailaddress.detailAddressAdapterDelegate
import ru.codeoverflow.openspaceapp.viewmodel.detailaddress.DetailsAddressViewModel

class DetailAddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_detail_address

    private val args: DetailAddressFragmentArgs by navArgs()

    private val vm: DetailsAddressViewModel by viewModel()

    private val adapter: ListDelegationAdapter<List<MeterModel>> by lazy {
        ListDelegationAdapter<List<MeterModel>>(
            detailAddressAdapterDelegate()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args.addressModel) {

            tvTotal.text =
                requireContext().getString(R.string.detail_address_total, totalPrice.format())
            setTitle(address)
            rvMeter.adapter = adapter
            adapter.items = listMeter
        }
    }

}