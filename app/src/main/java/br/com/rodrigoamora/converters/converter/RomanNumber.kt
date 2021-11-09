package br.com.rodrigoamora.converters.converter

class RomanNumber {
    companion object{
        fun converter(decimalNumber: String): String {
            val vaNum = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)

            val vaRom = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

            var result = ""
            while (true) {
                var numero: Int = decimalNumber.toInt()
                if (numero == 0) break
                System.out.printf("%-4d ", numero)
                var i = 0
                while (numero > 0) {
                    if (numero >= vaNum[i]) {
                        //print(vaRom[i])
                        result += vaRom[i]
                        numero -= vaNum[i]
                    } else {
                        i++
                    }
                }
                //println()
            }
            return result
        }
    }
}