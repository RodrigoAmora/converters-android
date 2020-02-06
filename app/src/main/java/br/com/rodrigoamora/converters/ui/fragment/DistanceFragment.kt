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
import kotlinx.android.synthetic.main.fragment_distance.*
import kotlinx.android.synthetic.main.fragment_distance.bt_convert
import kotlinx.android.synthetic.main.fragment_temperture.*
import kotlinx.android.synthetic.main.fragment_temperture.result as result1

class DistanceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_distance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val list = listOf(getString(R.string.kilometers_to_miles), getString(R.string.miles_to_kilometers))

        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, list)
        spinner_convert.setAdapter(arrayAdapter)

        spinner_convert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        distance.hint = getString(R.string.distance_in_kilometers)
                    }
                    1 -> {
                        distance.hint = getString(R.string.distance_in_miles)
                    }
                }
            }
        }

        bt_convert.setOnClickListener{
            convertDistance()
        }
    }

    private fun convertDistance() {
        val distanceConverter = DistanceConverter()
        var distanceConverted :Double = 0.0

        when(spinner_convert.selectedItemPosition) {
            0 -> {
                distanceConverted = distanceConverter.kilometerToMile(distance.text.toString().toDouble())
            }

             1 -> {
                 distanceConverted = distanceConverter.mileToKilometer(distance.text.toString().toDouble())
             }
        }

        result.text = distanceConverted.toString()

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        result.startAnimation(fadeIn)
    }
}