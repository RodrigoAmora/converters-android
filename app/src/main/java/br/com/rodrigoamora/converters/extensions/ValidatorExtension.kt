package br.com.rodrigoamora.converters.extensions

fun valueValidator(value :String) :Boolean {
    if (value.isEmpty()) {
        return false
    }
    return true
}