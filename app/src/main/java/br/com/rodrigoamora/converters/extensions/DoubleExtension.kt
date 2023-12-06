package br.com.rodrigoamora.converters.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Double.formatResult(): Double {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}

fun Double.formatTemperatureInCelsius(): String {
    return "$this ºC"
}

fun Double.formatTemperatureInFahrenheit(): String {
    return "$this ºF"
}