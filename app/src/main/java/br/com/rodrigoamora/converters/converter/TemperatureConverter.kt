package br.com.rodrigoamora.converters.converter

class TemperatureConverter {

    fun celsuisToFahrenheit(celsius: Double): Double {
        return (celsius * 9/5) + 32
    }

    fun fahrenheitToCelsuis(fahrenheit: Double): Double {
        return ((fahrenheit - 32)*5)/9
    }
}