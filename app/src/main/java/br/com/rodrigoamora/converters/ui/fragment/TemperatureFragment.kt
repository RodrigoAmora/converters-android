package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import kotlinx.android.synthetic.main.fragment_celsius_to_farenheit.*

class TemperatureFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_celsius_to_farenheit, container, false)

        val list = listOf(getString(R.string.celsius_to_fahrenheit), getString(R.string.fahrenheit_to_celsius))

        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, list)
        val convertSpinner = view.findViewById<Spinner>(R.id.convert)
        convertSpinner.setAdapter(arrayAdapter)

        val btConvert = view.findViewById<Button>(R.id.bt_convert)
        btConvert.setOnClickListener{
            var converterSelected = convertSpinner.selectedItemPosition
            var temperatureConverter = TemperatureConverter()

            var temperatureConverted:Double = 0.0
            if (converterSelected == 0) {
                temperatureConverted = temperatureConverter.celsuisToFahrenheit(temperature.text.toString().toDouble())
            } else if (converterSelected == 1) {
                temperatureConverted = temperatureConverter.fahrenheitToCelsuis(temperature.text.toString().toDouble())
            }

            result.setText(temperatureConverted.toString())
        }

        return view
    }

}
