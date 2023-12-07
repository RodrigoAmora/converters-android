package br.com.rodrigoamora.converters.converter

import org.junit.Assert
import org.junit.Test

class RomanNumberConverterTest {

    private val romanNumberConverter: RomanNumberConverter by lazy {
        RomanNumberConverter()
    }

    @Test
    fun testDecimalToRomanNumber() {
        val numberConverted = this.romanNumberConverter.convertDecimalToRomanNumber(3)
        Assert.assertEquals("III", numberConverted)
    }

}
