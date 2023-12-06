package br.com.rodrigoamora.converters.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtil {
    companion object {
        fun hideKeyboard(context: Context, view: View) {
            val imm : InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}
