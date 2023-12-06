package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.databinding.FragmentDistanceBinding
import br.com.rodrigoamora.converters.delegate.ViewDelegate
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import br.com.rodrigoamora.converters.ui.viewmodel.DistanceViewModel
import br.com.rodrigoamora.converters.util.KeyboardUtil
import org.koin.android.ext.android.inject

class DistanceFragment: Fragment(), ViewDelegate {

    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!

    private lateinit var btConvert: Button
    private lateinit var inputDistance: EditText
    private lateinit var spinnerConvert: Spinner
    private lateinit var tvResult: TextView

    private val distanceViewModel: DistanceViewModel by inject()
    private val mainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        this.initViews()
        this.setDelegate()
        return binding.root
    }

    private fun initViews() {
        val distanceOptions = resources.getStringArray(R.array.array_distance_options)

        this.inputDistance = binding.inputDistance
        this.tvResult = binding.tvResult

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, distanceOptions) }
        this.spinnerConvert = binding.spinnerConvert
        this.spinnerConvert.adapter = arrayAdapter

        this.spinnerConvert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        inputDistance.hint = getString(R.string.distance_in_kilometers)
                    }
                    1 -> {
                        inputDistance.hint = getString(R.string.distance_in_miles)
                    }
                }
            }
        }

        this.btConvert = binding.btConvert
        this.btConvert.setOnClickListener{
            mainActivity.let { it1 -> KeyboardUtil.hideKeyboard(it1, btConvert) }
            convertDistance()
        }
    }

    private fun setDelegate() {
        this.distanceViewModel.setDelegate(this)
    }

    private fun convertDistance() {
        val distanceTyped = inputDistance.text.toString()

        if (distanceTyped.isNotEmpty()) {
            when (spinnerConvert.selectedItemPosition) {
                0 -> {
                    this.distanceViewModel.kilometerToMile(distanceTyped.toDouble())
                }

                1 -> {
                    this.distanceViewModel.mileToKilometer(distanceTyped.toDouble())
                }
            }
        } else {
            this.showError(getString(R.string.error_value_is_empty))
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this.mainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showResult(result: String) {
        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        this.tvResult.startAnimation(fadeIn)
        this.tvResult.visibility = View.VISIBLE
        this.tvResult.text = result
    }
}