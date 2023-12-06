package br.com.rodrigoamora.converters.extensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun changeFragment(fragment: Fragment, fragmentManager: AppCompatActivity, container: Int, params: Bundle?) {
    val manager = fragmentManager.supportFragmentManager

    val transaction = manager.beginTransaction()
    transaction.replace(container, fragment)
    transaction.addToBackStack(null)

    if (params != null) {
        fragment.arguments = params
    }

    transaction.commit()
}