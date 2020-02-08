package br.com.rodrigoamora.converters.converter

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TemperatureConverterTest {

    var temperatureConverter :TemperatureConverter? = null

    @Before
    fun init() {
        temperatureConverter = TemperatureConverter()
    }

    @Test
    fun testConvertercelsuisToFahrenheitWithPositiveTemperature() {
        val temperatue1 = temperatureConverter?.celsuisToFahrenheit(10.0);
        val temperatue2 = temperatureConverter?.celsuisToFahrenheit(32.0);

        assertEquals(50.0, temperatue1!!, 0.0001)
        assertEquals(89.6, temperatue2!!, 0.0001)
    }
}