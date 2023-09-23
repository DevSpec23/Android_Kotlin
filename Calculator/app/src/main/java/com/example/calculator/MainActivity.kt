package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    //Variables
    private var tvInput: TextView? = null
    var lastNumeric : Boolean = true
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastDot = false
        lastNumeric = true
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){

            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                var splitValue = tvValue.split("-")
                var one = splitValue[0]
                var two = splitValue[1]

                tvInput?.text = (one.toInt() - two.toInt()).toString()
                lastNumeric = true
                lastDot = false
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

    }

    private fun isOperatorAdded(value : String) : Boolean {
        return if(value.startsWith("-")){
            false
        }
        else
        {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")
        }
    }
}