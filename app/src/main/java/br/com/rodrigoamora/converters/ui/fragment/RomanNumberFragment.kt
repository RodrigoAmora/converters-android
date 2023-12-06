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
import br.com.rodrigoamora.converters.databinding.FragmentRomanNumberBinding
import br.com.rodrigoamora.converters.delegate.ViewDelegate
import br.com.rodrigoamora.converters.ui.activity.MainActivity
import br.com.rodrigoamora.converters.ui.viewmodel.RomanNumberViewModel
import br.com.rodrigoamora.converters.util.KeyboardUtil
import org.koin.android.ext.android.inject

class RomanNumberFragment: Fragment(), ViewDelegate {

    private var _binding: FragmentRomanNumberBinding? = null
    private val binding get() = _binding!!

    private lateinit var btConvert: Button
    private lateinit var inputNumber: EditText
    private lateinit var tvResult: TextView
    private lateinit var spinnerConvert: Spinner

    private val romanNumberViewModel: RomanNumberViewModel by inject()
    private val mainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRomanNumberBinding.inflate(inflater, container, false)
        this.initViews()
        this.setDelegate()
        return binding.root
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
            activity?.let { it1 -> KeyboardUtil.hideKeyboard(it1, btConvert) }
            convertNumber()
        }
    }

    private fun setDelegate() {
        this.romanNumberViewModel.setDelegate(this)
    }

    private fun convertNumber() {
        val numberTyped = inputNumber.text.toString()

        if (numberTyped.isNotEmpty()) {
            when (spinnerConvert.selectedItemPosition) {
                0 -> {
                    this.romanNumberViewModel.convertDecimalToRomanNumber(numberTyped.toInt())
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