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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        label_version.setText(getVersionName().toString())
    }

    fun getVersionName(): String {
        try {
            val packageManager = activity?.packageManager
            val pInfo = packageManager?.getPackageInfo(activity?.packageName, 0)
            return pInfo?.versionName.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
            return "";
        }
    }
}