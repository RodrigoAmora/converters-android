package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import kotlinx.android.synthetic.main.fragment_celsius_to_farenheit.*

class TemperatureFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_celsius_to_farenheit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val list = listOf(getString(R.string.celsius_to_fahrenheit), getString(R.string.fahrenheit_to_celsius))

        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, list)
        convert.setAdapter(arrayAdapter)

        bt_convert.setOnClickListener{
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val converterSelected = convert.selectedItemPosition
        val temperatureConverter = TemperatureConverter()

        var temperatureConverted:Double = 0.0
        when(converterSelected) {
            0 -> {
                temperatureConverted = temperatureConverter.celsuisToFahrenheit(temperature.text.toString().toDouble())
            }

            1 -> {
                temperatureConverted = temperatureConverter.fahrenheitToCelsuis(temperature.text.toString().toDouble())
            }
        }

        result.setText(temperatureConverted.toString())
    }
}
