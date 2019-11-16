package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import kotlinx.android.synthetic.main.fragment_temperture.*

class TemperatureFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_temperture, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val list = listOf(getString(R.string.celsius_to_fahrenheit), getString(R.string.fahrenheit_to_celsius))

        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, list)
        convert.setAdapter(arrayAdapter)

        convert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        temperature.hint = getString(R.string.temperture_in_celsius)
                    }

                    1 -> {
                        temperature.hint = getString(R.string.temperture_in_fahrenheit)
                    }
                }
            }
        }

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
