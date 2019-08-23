package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import kotlinx.android.synthetic.main.fragment_celsius_to_farenheit.*

class CelsiusToFarenheitFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_celsius_to_farenheit, container, false)

        val btConvert = view.findViewById<Button>(R.id.bt_convert)
        btConvert.setOnClickListener{
            val temperatureConverter = TemperatureConverter()
            val fahrenheit = temperatureConverter.celsuisToFahrenheit(temperature.text.toString().toDouble())
            temperture_in_fahrenheit.setText(fahrenheit.toString())
        }

        return view
    }

}
