package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rodrigoamora.converters.databinding.FragmentAboutBinding
import br.com.rodrigoamora.converters.util.PackageInfoUtil

class AboutFragment: BaseFragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var lbVersion: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        this.lbVersion = binding.lbVersion
        this.lbVersion.text = PackageInfoUtil.getVersionName(this.mainActivity)
        return binding.root
    }

}