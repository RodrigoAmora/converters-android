package br.com.rodrigoamora.converters.ui.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rodrigoamora.converters.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    var myView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_celsius_to_farenheit, container, false)
        var versionName = getVersionName()
        label_version.setText(versionName.toString())
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        var versionName = getVersionName()
//        label_verion.setText(versionName.toString())
    }

    fun getVersionName(): String {
        try {
            val packageManager = activity.packageManager
            val pInfo = packageManager.getPackageInfo(activity.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
            return "";
        }
    }
}