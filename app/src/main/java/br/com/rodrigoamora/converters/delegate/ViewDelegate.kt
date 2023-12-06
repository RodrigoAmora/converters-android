package br.com.rodrigoamora.converters.delegate

interface ViewDelegate {
    fun showError(message: String)
    fun showResult(result: String)
}