package br.com.rodrigoamora.converters.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.ui.activity.MainActivity

open class BaseFragment: Fragment() {

    public val mainActivity by lazy {
        activity as MainActivity
    }

    fun showToast(message: String) {
        Toast.makeText(this.mainActivity, message, Toast.LENGTH_LONG).show()
    }

}