package ru.codeoverflow.openspaceapp.ui.list.address

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_address_item.view.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddAddress
import ru.codeoverflow.openspaceapp.entity.core.address.AddressModel
import ru.codeoverflow.openspaceapp.entity.core.address.BaseAddress
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel

fun addressAdapterDelegate(onClick: (AddressModel) -> Unit) =
    adapterDelegate<AddressModel, BaseAddress>(R.layout.rv_address_item) {

        val adapterMeter: ListDelegationAdapter<List<MeterModel>> = ListDelegationAdapter(
            addressMeterAdapterDelegate()
        )

        bind {
            with(itemView) {
                tvType.text = context.getString(item.type.typeNameId)
                ivIcon.setImageResource(item.type.imageId)
                tvAddress.text = item.address

                rvAddressMeter.adapter = adapterMeter
                adapterMeter.items = item.listMeter

                setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

fun addressAddAdapterDelegate(onClick: () -> Unit) =
    adapterDelegate<AddAddress, BaseAddress>(R.layout.layout_address_add_card) {

        bind {
            itemView.setOnClickListener {
                onClick.invoke()
            }
        }
    }

fun addressMeterAdapterDelegate() =
    adapterDelegate<MeterModel, MeterModel>(R.layout.rv_address_meter_item) {

        bind {
            with(itemView) {
                ivIcon.setImageResource(item.type.littleImageId)
                ivIcon.isActivated = item.price != null || item.value == null
            }
        }
    }