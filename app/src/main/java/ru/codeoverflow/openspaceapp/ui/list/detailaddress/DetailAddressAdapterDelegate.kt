package ru.codeoverflow.openspaceapp.ui.list.detailaddress

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.rv_detail_address_item.view.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.extension.format

fun detailAddressAdapterDelegate() =
    adapterDelegate<MeterModel, MeterModel>(R.layout.rv_detail_address_item) {

        bind {
            with(itemView) {
                ivIcon.setImageResource(item.type.imageId)
                ivIcon.isActivated = item.price != null || item.value == null
                tvType.text = context.getString(item.type.typeNameId)

                tvValue.text = if (item.value != null) context.getString(
                    R.string.detail_address_value_template,
                    item.value.format()
                ) else context.getString(R.string.detail_address_value_empty)
                tvPay.isVisible = item.value != null
                btnScanner.isVisible = item.value == null

                tvPay.text = if (item.price != null) {
                    context.getString(R.string.default_price, item.price.format())
                } else {
                    context.getString(R.string.detail_address_payed)
                }

            }
        }
    }