package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*
import java.text.SimpleDateFormat


class ActivityPesanFutsal : AppCompatActivity() {

    var edittext : EditText?=null
    var myCalendar = Calendar.getInstance()
    var curCalendar = Calendar.getInstance()
    var curDay=""
    var curMonth=""
    var curYear=""
    private val TAG = "Disini act futsal "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_futsal)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //initDate()

        edittext = findViewById(R.id.edit_tgl) as EditText

        edittext!!.hint = SimpleDateFormat("EEEE, dd/MMM/yy", Locale.getDefault()).format(curCalendar.time)

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            Log.d(TAG, "$dayOfMonth/${monthOfYear+1}/$year")
            if(myCalendar>=curCalendar) {
                updateLabel()
            } else {
                Toast.makeText(this@ActivityPesanFutsal, "Invalid Date", Toast.LENGTH_SHORT).show()
            }
        }

        edittext!!.setOnClickListener{

                // TODO Auto-generated method stub
                DatePickerDialog(
                        this@ActivityPesanFutsal,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
        }

    }

    private fun updateLabel() {
        val myFormat = "EEEE, dd/MMM/yyyy" //In which you need put here
        //val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        edittext!!.setText(sdf.format(myCalendar.time))
        //Log.d(TAG,"tgl = " + sdf.format(curCalendar.time))
    }

    private fun initDate(){
        curDay = SimpleDateFormat("dd", Locale.getDefault()).format(curCalendar.time)
        curMonth = SimpleDateFormat("MM", Locale.getDefault()).format(curCalendar.time)
        curYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(curCalendar.time)

        Log.d(TAG,"tgl = $curDay/$curMonth/$curYear")
    }
}
