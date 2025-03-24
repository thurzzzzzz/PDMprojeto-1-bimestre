package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editPeso = findViewById<TextInputEditText>(R.id.textInputEditText)
        val editAltura = findViewById<TextInputEditText>(R.id.textInputEditText2)
        val textIMC = findViewById<TextView>(R.id.textView4)
        val textClassificacao = findViewById<TextView>(R.id.textView6)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcularIMC(editPeso, editAltura, textIMC, textClassificacao)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        editPeso.addTextChangedListener(textWatcher)
        editAltura.addTextChangedListener(textWatcher)
    }

    private fun calcularIMC(
        editPeso: TextInputEditText,
        editAltura: TextInputEditText,
        textIMC: TextView,
        textClassificacao: TextView
    ) {
        val peso = editPeso.text.toString().toFloatOrNull()
        val altura = editAltura.text.toString().toFloatOrNull()

        if (peso != null && altura != null && altura > 0) {
            val imc = peso / (altura * altura)
            textIMC.text = String.format("%.2f", imc)
            textClassificacao.text = classificarIMC(imc)
        } else {
            textIMC.text = "0.0"
            textClassificacao.text = "Digite valores vÃ¡lidos"
        }
    }

    private fun classificarIMC(imc: Float): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade Grau I"
            imc in 35.0..39.9 -> "Obesidade Grau II"
            else -> "Obesidade Grau III"
        }
    }
}
