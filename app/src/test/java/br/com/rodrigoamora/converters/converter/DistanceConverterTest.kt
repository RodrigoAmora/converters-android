package br.com.rodrigoamora.converters.converter

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DistanceConverterTest {

    private val distanceConverter by lazy {
        DistanceConverter()
    }

    @Before
    fun init() {}

    @Test
    fun testConvertKilometerToMile() {
        val distance1 = this.distanceConverter.kilometerToMile(1.0)
        val distance2 = this.distanceConverter.kilometerToMile(2.0)

        assertEquals(0.621371, distance1, 0.0001)
        assertEquals(1.242742, distance2, 0.0001)
    }

    @Test
    fun testConvertMileToKilometer() {
        val distance1 = this.distanceConverter.mileToKilometer(1.0)
        val distance2 = this.distanceConverter.mileToKilometer(2.0)

        assertEquals(1.609344, distance1, 0.0001)
        assertEquals(3.218688, distance2, 0.0001)
    }
}