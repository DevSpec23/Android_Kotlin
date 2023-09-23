package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var convertedVal : TextView? = null
    private var age : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selectedDate);
        convertedVal = findViewById(R.id.convertedValue)
        age = findViewById(R.id.age)

        btnDatePicker.setOnClickListener{

            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        //variables
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        var month = myCalender.get(Calendar.MONTH)
        var day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                Toast.makeText(this,
                    "Year was $year, month was ${month}, day $dayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selected_Date = "$dayOfMonth/${month+1}/$year"

                selectedDate?.setText(selected_Date)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selected_Date)

                theDate?.let{
                    val convertToMin = theDate.time / 60000

                    val curDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    curDate?.let{
                        val curDateInMin = curDate.time/60000

                        val diffInMin = curDateInMin - convertToMin

                        convertedVal?.text = diffInMin.toString()
                    }
                }
            },
            year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()
    }
}