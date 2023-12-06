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
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.databinding.FragmentTempertureBinding
import br.com.rodrigoamora.converters.delegate.ViewDelegate
import br.com.rodrigoamora.converters.extensions.valueValidator
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import br.com.rodrigoamora.converters.ui.viewmodel.TemperatureViewModel
import br.com.rodrigoamora.converters.util.KeyboardUtil
import org.koin.android.ext.android.inject


class TemperatureFragment: Fragment(), ViewDelegate {

    private var _binding: FragmentTempertureBinding? = null
    private val binding get() = _binding!!

    private lateinit var btConvert: Button
    private lateinit var temperature: EditText
    private lateinit var tvResult: TextView
    private lateinit var spinnerConvert: Spinner

    private val temperatureViewModel: TemperatureViewModel by inject()
    private val mainActivity: MainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTempertureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initViews()
        this.setDelegate()
    }

    private fun initViews() {
        val temperatureOptions = resources.getStringArray(R.array.array_temperature_options)

        this.temperature = binding.temperature
        this.tvResult = binding.tvResult

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, temperatureOptions) }
        this.spinnerConvert = binding.convert
        this.spinnerConvert.adapter = arrayAdapter

        this.spinnerConvert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        this.btConvert = binding.btConvert
        this.btConvert.setOnClickListener{
            activity?.let { it1 -> KeyboardUtil.hideKeyboard(it1, btConvert) }
            convertTemperature()
        }
    }

    private fun setDelegate() {
        this.temperatureViewModel.setDelegate(this)
    }

    private fun convertTemperature() {
        val converterSelected = spinnerConvert.selectedItemPosition

        val temperatureTyped = temperature.text.toString()

        if (valueValidator(temperatureTyped)) {
            when (converterSelected) {
                0 -> {
                    this.temperatureViewModel.celsiusToFahrenheit(temperatureTyped.toDouble())
                }

                1 -> {
                    this.temperatureViewModel.fahrenheitToCelsius(temperatureTyped.toDouble())
                }
            }
        } else {
            this.showError(getString(R.string.error_value_is_empty))
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this.mainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showResult(result: String) {
        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        this.tvResult.startAnimation(fadeIn)
        this.tvResult.visibility = View.VISIBLE
        this.tvResult.text = result
    }
}
