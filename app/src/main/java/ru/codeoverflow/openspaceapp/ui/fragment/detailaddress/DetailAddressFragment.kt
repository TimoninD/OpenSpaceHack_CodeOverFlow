package ru.codeoverflow.openspaceapp.ui.fragment.detailaddress

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_address.*
import kotlinx.android.synthetic.main.fragment_detail_address.*
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressItem
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressType
import ru.codeoverflow.openspaceapp.ui.list.detailaddress.detailAddressAdapterDelegate

class DetailAddressFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_detail_address

    private val testList = listOf(
        DetailAddressItem(DetailAddressType.HOT_WATER, 15, 120.203f),
        DetailAddressItem(DetailAddressType.COLD_WATER, 20, null),
        DetailAddressItem(DetailAddressType.GAS, 5, null),
        DetailAddressItem(DetailAddressType.LIGHTNING, null, null)

    )

    private val adapter: ListDelegationAdapter<List<DetailAddressItem>> by lazy {
        ListDelegationAdapter<List<DetailAddressItem>>(
            detailAddressAdapterDelegate()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMeter.adapter = adapter
        adapter.items = testList
    }

}