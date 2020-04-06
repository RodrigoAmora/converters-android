package br.com.rodrigoamora.converters.shared.extension

fun valueValidator(value :String) :Boolean {
    if (value.isEmpty()) {
        return false
    }

    return true
}