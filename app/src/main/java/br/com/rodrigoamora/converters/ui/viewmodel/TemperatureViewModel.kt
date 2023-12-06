package br.com.rodrigoamora.converters.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.rodrigoamora.converters.converter.TemperatureConverter
import br.com.rodrigoamora.converters.delegate.ViewDelegate
import br.com.rodrigoamora.converters.extensions.formatResult
import br.com.rodrigoamora.converters.extensions.formatTemperatureInCelsius
import br.com.rodrigoamora.converters.extensions.formatTemperatureInFahrenheit

class TemperatureViewModel: ViewModel() {

    private lateinit var viewDelegate: ViewDelegate
    private val temperatureConverter by lazy {
        TemperatureConverter()
    }

    fun setDelegate(viewDelegate: ViewDelegate) {
        this.viewDelegate = viewDelegate
    }

    fun celsiusToFahrenheit(temperature: Double) {
        val temperatureConverted = this.temperatureConverter.celsiusToFahrenheit(temperature)
                                                                    .formatResult()
                                                                    .formatTemperatureInFahrenheit()
        this.viewDelegate.showResult(temperatureConverted)
    }

    fun fahrenheitToCelsius(temperature: Double) {
        val temperatureConverted = this.temperatureConverter.fahrenheitToCelsius(temperature)
                                                                    .formatResult()
                                                                    .formatTemperatureInCelsius()
        this.viewDelegate.showResult(temperatureConverted)
    }
}