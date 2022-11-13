package com.example.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val EGP by lazy { getString(R.string.egyptian_pound_egp) }
    val USD by lazy { getString(R.string.american_dollar_usd) }
    val SAR by lazy { getString(R.string.Saudia_Ryal) }
    val KWD by lazy { getString(R.string.Kuwaiti_Dinar) }

    val listValue by lazy{ mapOf(
    USD to 0.052356,
    EGP to 1.0,
    SAR to 0.197040,
    KWD to 0.0166838
    )}

    lateinit var convertButton: Button
    lateinit var amount: TextInputEditText
    lateinit var result: TextInputEditText
    lateinit var from: AutoCompleteTextView
    lateinit var to: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initalizeViews()
        populateMenu()


        convertButton.setOnClickListener {

            calcResault()
        }

    }

    private fun initalizeViews() {
        convertButton = findViewById(R.id.button)
        amount = findViewById(R.id.AmountTIET)
        result = findViewById(R.id.ResultTIET)
        from = findViewById(R.id.FromACTV)
        to = findViewById(R.id.ToACTV)



    }

    private fun populateMenu() {
        val currencyList = listOf(EGP, USD, SAR, KWD)
        val adapter = ArrayAdapter(this, R.layout.list_currency, currencyList)
        from.setAdapter(adapter)
        to.setAdapter(adapter)


    }

    private fun calcResault(){
        if (amount.text.toString().isNotEmpty()) {
            result.setText(
                String.format(
                    "%.2f", listValue.get(to.text.toString())!!.times(
                        amount.text.toString().toDouble()
                            .div(listValue.get(from.text.toString())!!)
                    )
                )
            )
        } else {
            amount.setError(getString(R.string.amount_required))
        }
    }


}