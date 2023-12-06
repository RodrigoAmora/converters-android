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
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.DistanceConverter
import br.com.rodrigoamora.converters.databinding.FragmentDistanceBinding
import br.com.rodrigoamora.converters.extensions.hideKeyboard
import br.com.rodrigoamora.converters.extensions.valueValidator
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import java.math.BigDecimal
import java.math.RoundingMode

class DistanceFragment: Fragment() {

    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!

    private lateinit var btConvert: Button
    private lateinit var inputDistance: EditText
    private lateinit var spinnerConvert: Spinner
    private lateinit var tvResult: TextView

    private val mainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initViews()
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
            mainActivity?.let { it1 -> hideKeyboard(it1, btConvert) }
            convertDistance()
        }
    }

    private fun convertDistance() {
        val distanceConverter = DistanceConverter()
        val distanceValue = inputDistance?.text.toString()
        var distanceConverted: BigDecimal = BigDecimal.ZERO

        var result = ""
        if (valueValidator(distanceValue)) {
            when (spinnerConvert.selectedItemPosition) {
                0 -> {
                    distanceConverted = BigDecimal(distanceConverter
                                                    .kilometerToMile(distanceConverted.toDouble()))
                                            .setScale(2, RoundingMode.HALF_EVEN)
                }

                1 -> {
                    distanceConverted = BigDecimal(distanceConverter
                                                    .mileToKilometer(distanceConverted.toDouble()))
                                            .setScale(2, RoundingMode.HALF_EVEN)
                }
            }

            result = getString(R.string.result, distanceConverted.toString())
        } else {
            result = getString(R.string.error_value_is_empty)
            tvResult.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        tvResult.visibility = View.VISIBLE
        tvResult.text = result

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        tvResult.startAnimation(fadeIn)
    }
}