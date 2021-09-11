package br.com.rodrigoamora.converters.converter

class DistanceConverter {

    fun kilometerToMile(distance: Double): Double {
        return distance * 0.621371
    }

    fun mileToKilometer(distance: Double): Double {
        return distance * 1.609344
    }
}