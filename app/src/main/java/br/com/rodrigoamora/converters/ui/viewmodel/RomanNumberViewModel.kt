package br.com.rodrigoamora.converters.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.rodrigoamora.converters.converter.RomanNumberConverter
import br.com.rodrigoamora.converters.delegate.ViewDelegate

class RomanNumberViewModel: ViewModel() {

    private lateinit var viewDelegate: ViewDelegate
    private val romanNumberConverter by lazy {
        RomanNumberConverter()
    }

    fun setDelegate(viewDelegate: ViewDelegate) {
        this.viewDelegate = viewDelegate
    }

    fun convertDecimalToRomanNumber(decimal: Int?) {
        val numberConverted = this.romanNumberConverter.convertDecimalToRomanNumber(decimal)

        if (numberConverted.isNullOrEmpty()) {
            this.viewDelegate.showError("")
        } else {
            this.viewDelegate.showResult(numberConverted)
        }
    }

}
