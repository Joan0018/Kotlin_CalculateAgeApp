package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time/ 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

                val selectedDateInDay = theDate!!.time/ 86400000
                val currentDateToDay = currentDate!!.time/ 86400000
                val differenceInDay = currentDateToDay - selectedDateInDay

                tvSelectedDateInDay.setText(differenceInDay.toString())

                val selectedDateInYear = theDate!!.time/31536000000
                val currentDateToYear = currentDate!!.time/ 31536000000
                val differenceInYear = currentDateToYear - selectedDateInYear

                tvSelectedDateInYear.setText(differenceInYear.toString())

            }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}