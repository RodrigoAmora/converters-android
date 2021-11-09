package br.com.rodrigoamora.converters.converter

class RomanNumberConverter {

    private var number: Int? = null
    private val listRomanNumbers = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    private val listDecimalNumbers = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)

    fun convertDecimalToRomanNumber(decimal: Int?): String? {
        var decimal = decimal
        if (decimal != null && decimal > 0) {
            var romanNumber = ""

            for (x in listDecimalNumbers.indices) {
                val num = listDecimalNumbers[x]
                while (decimal >= num) {
                    romanNumber += listRomanNumbers[x]
                    decimal -= num
                }
            }

            return romanNumber
        }
        
        return null
    }

    fun <E> converter(): E? {
        return convertDecimalToRomanNumber(number) as E?
    }
}