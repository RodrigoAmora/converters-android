package br.com.rodrigoamora.converters.converter

class TemperatureConverter {

    fun celsuisToFahrenheit(celsius: Double): Double {
        return (celsius - 32) * 5/9;
    }
}