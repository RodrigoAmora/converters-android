package br.com.rodrigoamora.converters.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.rodrigoamora.converters.R

fun changeFragment(fragment: Fragment, fragmentManager: AppCompatActivity) {
    val manager = fragmentManager.supportFragmentManager
    val transaction = manager.beginTransaction()
    transaction.add(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}