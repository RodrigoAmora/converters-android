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
import br.com.rodrigoamora.converters.converter.RomanNumberConverter
import br.com.rodrigoamora.converters.databinding.FragmentRomanNumberBinding
import br.com.rodrigoamora.converters.extensions.hideKeyboard
import br.com.rodrigoamora.converters.extensions.valueValidator

class RomanNumberFragment : Fragment() {

    private var _binding: FragmentRomanNumberBinding? = null
    private val binding get() = _binding!!


    private lateinit var btConvert: Button
    private lateinit var inputNumber: EditText
    private lateinit var tvResult: TextView
    private lateinit var spinnerConvert: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRomanNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initViews()
    }

    private fun initViews() {
        val numberOptions = resources.getStringArray(R.array.array_roman_decimal_number)

        this.inputNumber = binding.inputNumber
        this.tvResult = binding.tvResult

        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, numberOptions) }
        this.spinnerConvert = binding.spinnerConvert
        this.spinnerConvert.adapter = arrayAdapter

        this.spinnerConvert.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        inputNumber.hint = getString(R.string.distance_in_kilometers)
                    }
                    1 -> {
                        inputNumber.hint = getString(R.string.distance_in_miles)
                    }
                }
            }
        }

        this.btConvert = binding.btConvert
        this.btConvert.setOnClickListener{
            activity?.let { it1 -> hideKeyboard(it1, btConvert) }
            convertNumber()
        }
    }

    private fun convertNumber() {
        val number = inputNumber.text.toString()
        var numberConverted: String = ""

        var result = ""
        if (valueValidator(number)) {
            val romanNumberConverter = RomanNumberConverter()

            when (spinnerConvert.selectedItemPosition) {
                0 -> {
                    numberConverted = romanNumberConverter
                                        .convertDecimalToRomanNumber(number.toInt())
                                        .toString()
                }
            }

            result = getString(R.string.result, numberConverted)
        } else {
            result = getString(R.string.error_value_is_empty)
            this.tvResult.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        this.tvResult.visibility = View.VISIBLE
        this.tvResult.text = result

        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        this.tvResult.startAnimation(fadeIn)
    }
}