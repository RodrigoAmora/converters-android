package br.com.rodrigoamora.converters.converter

class RomanNumberConverter {

    private var numero: Int? = null
    private val listaRomanos = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    private val listaArabicos = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)

    fun convertDecimalToRomanNumber(arabico: Int?): String? {
        var arabico = arabico
        if (arabico != null && arabico > 0) {
            var romano = ""

            for (x in listaArabicos.indices) {
                val num = listaArabicos[x]
                while (arabico >= num) {
                    romano += listaRomanos[x]
                    arabico -= num
                }
            }
            return romano
        }
        return null
    }

    fun <E> converter(): E? {
        return convertDecimalToRomanNumber(numero) as E?
    }
}