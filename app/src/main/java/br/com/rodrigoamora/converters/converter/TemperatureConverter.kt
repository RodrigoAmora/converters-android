package br.com.rodrigoamora.converters.converter

class TemperatureConverter {

    fun celsuisToFahrenheit(celsius: Double): Double {
        var fahrenheit = (celsius - 32) * 5/9;
        return fahrenheit;
    }
}