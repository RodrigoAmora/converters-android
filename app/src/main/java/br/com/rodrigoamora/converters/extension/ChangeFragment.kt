package br.com.rodrigoamora.converters.extension

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

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