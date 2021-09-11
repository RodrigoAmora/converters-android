package br.com.rodrigoamora.converters.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.converters.R
import br.com.rodrigoamora.converters.converter.DistanceConverter
import br.com.rodrigoamora.converters.extemsion.hideKeyboard
import br.com.rodrigoamora.converters.shared.extension.valueValidator
import kotlinx.android.synthetic.main.fragment_distance.*

class DistanceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_distance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val distanceOptions = resources.getStringArray(R.array.array_distance_options)

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, distanceOptions) }
        spinner_convert?.setAdapter(arrayAdapter)

        spinner_convert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        input_distance?.hint = getString(R.string.distance_in_kilometers)
                    }
                    1 -> {
                        input_distance?.hint = getString(R.string.distance_in_miles)
                    }
                }
            }
        }

        bt_convert?.setOnClickListener{
            activity?.let { it1 -> hideKeyboard(it1, bt_convert) }
            convertDistance()
        }
    }

    private fun convertDistance() {
        val distanceConverter = DistanceConverter()
        var distanceConverted: Double = 0.0
        val distanceValue = input_distance?.text.toString()

        var result = ""
        if (valueValidator(distanceValue)) {
            when (spinner_convert?.selectedItemPosition) {
                0 -> {
                    distanceConverted = distanceConverter.kilometerToMile(distanceValue.toDouble())
                }

                1 -> {
                    distanceConverted = distanceConverter.mileToKilometer(distanceValue.toDouble())
                }
            }

            result = getString(R.string.result, distanceConverted.toString())
        } else {
            result = getString(R.string.error_value_is_empty)
            tv_result?.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        tv_result?.visibility = View.VISIBLE
        tv_result?.text = result

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        tv_result?.startAnimation(fadeIn)
    }
}