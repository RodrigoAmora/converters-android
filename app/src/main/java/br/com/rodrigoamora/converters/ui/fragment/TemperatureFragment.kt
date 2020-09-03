package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import br.com.rodrigoamora.converters.extemsion.hideKeyboard
import br.com.rodrigoamora.converters.shared.extension.valueValidator
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

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, list) }
        convert?.setAdapter(arrayAdapter)

        convert?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        temperature?.hint = getString(R.string.temperature_in_celsius)
                    }

                    1 -> {
                        temperature?.hint = getString(R.string.temperature_in_fahrenheit)
                    }
                }
            }
        }

        bt_convert?.setOnClickListener{
            activity?.let { it1 -> hideKeyboard(it1, bt_convert) }
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val converterSelected = convert.selectedItemPosition
        val temperatureConverter = TemperatureConverter()
        var temperatureConverted:Double = 0.0
        val temperatureValue = temperature?.text.toString()

        var result = ""
        if (valueValidator(temperatureValue)) {
            when (converterSelected) {
                0 -> {
                    temperatureConverted = temperatureConverter.celsuisToFahrenheit(temperatureValue.toDouble())
                }

                1 -> {
                    temperatureConverted = temperatureConverter.fahrenheitToCelsuis(temperatureValue.toDouble())
                }
            }

            result = getString(R.string.result, temperatureConverted.toString())
        } else {
            result = getString(R.string.error_value_is_empty)
            tv_result?.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        tv_result?.startAnimation(fadeIn)

        tv_result?.visibility = View.VISIBLE
        tv_result?.setText(result)
    }
}
