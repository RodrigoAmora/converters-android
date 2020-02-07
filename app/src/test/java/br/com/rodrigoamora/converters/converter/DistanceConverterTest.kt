package br.com.rodrigoamora.converters.converter

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DistanceConverterTest {

    var distanceConverter :DistanceConverter? = null

    @Before
    fun init() {
        distanceConverter = DistanceConverter()
    }

    @Test
    fun testConvertKilometerToMile() {
        val distance1 = distanceConverter?.kilometerToMile(1.0)
        val distance2 = distanceConverter?.kilometerToMile(2.0)

        assertEquals(0.621371, distance1!!, 0.0001)
        assertEquals(1.242742, distance2!!, 0.0001)
    }

    @Test
    fun testConvertMileToKilometer() {
        val distance1 = distanceConverter?.mileToKilometer(1.0)
        val distance2 = distanceConverter?.mileToKilometer(2.0)

        assertEquals(1.609344, distance1!!, 0.0001)
        assertEquals(3.218688, distance2!!, 0.0001)
    }
}