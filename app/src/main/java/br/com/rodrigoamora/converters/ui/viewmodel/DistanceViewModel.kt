package br.com.rodrigoamora.converters.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.rodrigoamora.converters.converter.DistanceConverter
import br.com.rodrigoamora.converters.delegate.ViewDelegate
import br.com.rodrigoamora.converters.extensions.formatDistanceInKilometer
import br.com.rodrigoamora.converters.extensions.formatDistanceInMile
import br.com.rodrigoamora.converters.extensions.formatResult

class DistanceViewModel: ViewModel() {

    private lateinit var viewDelegate: ViewDelegate
    private val distanceConverter by lazy {
        DistanceConverter()
    }

    fun setDelegate(viewDelegate: ViewDelegate) {
        this.viewDelegate = viewDelegate
    }

    fun kilometerToMile(distance: Double) {
        val distanceConverted = this.distanceConverter.kilometerToMile(distance)
                                                              .formatResult()
                                                              .formatDistanceInMile()

        this.viewDelegate.showResult(distanceConverted)
    }

    fun mileToKilometer(distance: Double) {
        val distanceConverted = this.distanceConverter.mileToKilometer(distance)
                                                             .formatResult()
                                                             .formatDistanceInKilometer()

        this.viewDelegate.showResult(distanceConverted)
    }
}