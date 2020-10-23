package ru.codeoverflow.openspaceapp.ui.list.address

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_address_item.view.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.address.AddAddress
import ru.codeoverflow.openspaceapp.entity.core.address.AddressItem
import ru.codeoverflow.openspaceapp.entity.core.address.BaseAddress

fun addressAdapterDelegate(onClick: () -> Unit) =
    adapterDelegate<AddressItem, BaseAddress>(R.layout.rv_address_item) {

        bind {
            with(itemView) {
                tvType.text = context.getString(item.type.typeNameId)
                ivIcon.setImageResource(item.type.imageId)
                tvAddress.text = item.address
                setOnClickListener {
                    onClick.invoke()
                }
            }
        }
    }

fun addressAddAdapterDelegate(onClick: () -> Unit) =
    adapterDelegate<AddAddress, BaseAddress>(R.layout.rv_address_add_item) {

        bind {
            itemView.setOnClickListener {
                onClick.invoke()
            }
        }
    }