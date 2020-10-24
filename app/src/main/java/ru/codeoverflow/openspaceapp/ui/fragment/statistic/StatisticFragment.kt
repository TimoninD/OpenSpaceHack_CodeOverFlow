package ru.codeoverflow.openspaceapp.ui.fragment.statistic

import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate.rgb
import kotlinx.android.synthetic.main.fragment_statistic.*
import ru.codeoverflow.openspaceapp.R
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.DetailAddressType
import ru.codeoverflow.openspaceapp.entity.core.detailaddress.MeterModel
import ru.codeoverflow.openspaceapp.extension.format
import ru.codeoverflow.openspaceapp.ui.common.BaseFragment


class StatisticFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_statistic

    private val colorWhite = rgb("#FFFFFF")

    private val listColors by lazy {
        listOf(
            rgb("#FFD1C7"),
            rgb("#C3D4FF"),
            rgb("#C3C6FF"),
            rgb("#FFEFB5")
        )
    }

    private val testCurrentList = listOf(
        MeterModel(DetailAddressType.HOT_WATER, 15, 120.203f),
        MeterModel(DetailAddressType.COLD_WATER, 20, 40f),
        MeterModel(DetailAddressType.GAS, 5, 42f),
        MeterModel(DetailAddressType.LIGHTNING, null, 121f)
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPieChartView(testCurrentList)

        severityBarChart.data = BarData(
            BarDataSet(
                listOf(
                    BarEntry(10f, 20f),
                    BarEntry(30f, 40f),
                    BarEntry(50f, 60f),
                    BarEntry(70f, 80f),
                    BarEntry(90f, 100f)
                ),
                ""
            )
        )

        severityBarChart.description.isEnabled = false
        severityBarChart.setMaxVisibleValueCount(7)

        severityBarChart.setDrawBarShadow(false)

        severityBarChart.axisRight.isEnabled = false
        severityBarChart.animateY(1500)

        severityBarChart.legend.isEnabled = false
        severityBarChart.setTouchEnabled(false)
        severityBarChart.xAxis.isEnabled = true
        severityBarChart.xAxis.position = XAxisPosition.BOTTOM
        severityBarChart.invalidate()
    }

    private fun createPieChartView(list: List<MeterModel>) {
        val dataSet = PieDataSet(list.map {
            PieEntry(
                it.price ?: 0f,
                requireActivity().getString(it.type.typeNameId)
            )
        }, "")
        dataSet.colors = listColors
        dataSet.valueTextColor = colorWhite
        dataSet.valueTextSize = 8f
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return getString(
                    R.string.default_price,
                    pieChart.defaultValueFormatter.getFormattedValue(value).toFloatOrNull().format()
                )
            }
        }
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
        pieChart.setDrawEntryLabels(false)
        pieChart.contentDescription = ""
        pieChart.holeRadius = 40f
        pieChart.description.isEnabled = false


        val legend = pieChart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.yEntrySpace = 8f
        legend.textColor = resources.getColor(R.color.second_text_color, null)
        legend.textSize = 12f
        legend.formSize = 10f
        legend.formToTextSpace = 12f
    }
}