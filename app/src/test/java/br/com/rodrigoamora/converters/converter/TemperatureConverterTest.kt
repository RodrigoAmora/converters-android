package br.com.rodrigoamora.converters.converter

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TemperatureConverterTest {

    private val temperatureConverter: TemperatureConverter by lazy {
        TemperatureConverter()
    }

    @Before
    fun init() {}

    @Test
    fun testConverterCelsuisToFahrenheitWithPositiveTemperature() {
        val temperatue1 = this.temperatureConverter.celsiusToFahrenheit(10.0)
        val temperatue2 = this.temperatureConverter.celsiusToFahrenheit(32.0)

        assertEquals(50.0, temperatue1, 0.0001)
        assertEquals(89.6, temperatue2, 0.0001)
    }

    @Test
    fun testConverterCelsuisToFahrenheitWithNegativeTemperature() {
        val temperatue1 = this.temperatureConverter.celsiusToFahrenheit(-15.0)
        val temperatue2 = this.temperatureConverter.celsiusToFahrenheit(-30.0)

        assertEquals(5.0, temperatue1, 0.0001)
        assertEquals(-22.0, temperatue2, 0.0001)
    }

    @Test
    fun testConverterFahrenheitToCelsuisWithPositiveTemperature() {
        val temperatue1 = this.temperatureConverter.fahrenheitToCelsius(32.0)
        val temperatue2 = this.temperatureConverter.fahrenheitToCelsius(110.0)

        assertEquals(0.0, temperatue1, 0.0001)
        assertEquals(43.333333333333336, temperatue2, 0.0001)
    }

    @Test
    fun testConverterFahrenheitToCelsuisWithNegativeTemperature() {
        val temperatue1 = this.temperatureConverter.fahrenheitToCelsius(-32.0)

        assertEquals(-35.55555555555556, temperatue1, 0.0001)
    }
}