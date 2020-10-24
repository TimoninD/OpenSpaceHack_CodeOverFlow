package ru.codeoverflow.openspaceapp.ui.fragment.statistic

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.rgb
import kotlinx.android.synthetic.main.fragment_statistic.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressType
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment


class StatisticFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_statistic

    private val listColors by lazy {
        listOf(
            rgb("#FFD1C7"),
            rgb("#C3D4FF"),
            rgb("#C3C6FF"),
            rgb("#FFEFB5")
        )
    }

    private val testList = listOf(
        MeterModel(DetailAddressType.HOT_WATER, 15, 120.203f),
        MeterModel(DetailAddressType.COLD_WATER, 20, 40f),
        MeterModel(DetailAddressType.GAS, 5, 12f),
        MeterModel(DetailAddressType.LIGHTNING, null, 121f)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSet = PieDataSet(testList.map {
            PieEntry(
                it.price ?: 0f,
                requireActivity().getString(it.type.typeNameId)
            )
        }, "")
        dataSet.colors = listColors
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
        pieChart.centerText = "50% \n "
        pieChart.setDrawEntryLabels(false)
        pieChart.contentDescription = ""
        pieChart.setEntryLabelTextSize(12f)
        pieChart.holeRadius = 45f
        pieChart.description.isEnabled = false


        val legend = pieChart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.textSize = 12f
        legend.formSize = 20f
        legend.formToTextSpace = 2f
    }
}