package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import br.com.rodrigoamora.converters.databinding.FragmentTempertureBinding
import br.com.rodrigoamora.converters.extensions.hideKeyboard
import br.com.rodrigoamora.converters.extensions.valueValidator
import java.math.BigDecimal
import java.math.RoundingMode


class TemperatureFragment : Fragment() {

    private var _binding: FragmentTempertureBinding? = null
    private val binding get() = _binding!!

    private lateinit var btConvert: Button
    private lateinit var temperature: EditText
    private lateinit var tvResult: TextView
    private lateinit var spinnerConvert: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTempertureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initViews()
    }

    private fun initViews() {
        val temperatureOptions = resources.getStringArray(R.array.array_temperature_options)

        tvResult = binding.tvResult

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, temperatureOptions) }
        spinnerConvert = binding.convert
        spinnerConvert.adapter = arrayAdapter

        spinnerConvert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}

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

        btConvert = binding.btConvert
        btConvert?.setOnClickListener{
            activity?.let { it1 -> hideKeyboard(it1, btConvert) }
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val converterSelected = spinnerConvert.selectedItemPosition
        val temperatureConverter = TemperatureConverter()
        val temperatureValue = temperature?.text.toString()
        var temperatureConverted: BigDecimal = BigDecimal.ZERO

        var result = ""
        if (valueValidator(temperatureValue)) {
            when (converterSelected) {
                0 -> {
                    temperatureConverted = BigDecimal(temperatureConverter
                                                        .fahrenheitToCelsius(temperatureValue.toDouble()))
                                            .setScale(2, RoundingMode.HALF_EVEN)
                }

                1 -> {
                    temperatureConverted = BigDecimal(temperatureConverter
                                                        .fahrenheitToCelsius(temperatureValue.toDouble()))
                                            .setScale(2, RoundingMode.HALF_EVEN)
                }
            }

            result = getString(R.string.result, temperatureConverted.toString())
        } else {
            result = getString(R.string.error_value_is_empty)
            tvResult.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        tvResult.startAnimation(fadeIn)
        tvResult.visibility = View.VISIBLE
        tvResult.text = result
    }
}
