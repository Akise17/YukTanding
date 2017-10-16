package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.app.DatePickerDialog
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import java.util.*
import java.text.SimpleDateFormat
import android.widget.TimePicker
import android.app.TimePickerDialog
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import kotlin.collections.ArrayList


class ActivityPesanFutsal : AppCompatActivity() {

    private var edit_tanggal: EditText?=null
    private var edit_jam: EditText?=null
    private var filter: RelativeLayout?=null
    internal lateinit var VenueArrayList: ArrayList<ItemVenue>
    internal lateinit var VenueRecyclerView: RecyclerView
    private var myCalendar = Calendar.getInstance()
    private var curCalendar = Calendar.getInstance()
    private var curDay=""
    private var curMonth=""
    private var curYear=""
    private val TAG = "Disini act futsal "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_futsal)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val filter = findViewById(R.id.venue_filter) as RelativeLayout
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //=======================================Date===============================================
        edit_tanggal = findViewById(R.id.edit_tgl) as EditText
        edit_jam = findViewById(R.id.edit_jam) as EditText

        edit_tanggal!!.hint = SimpleDateFormat("EEEE, dd/MMM/yy", Locale.getDefault()).format(curCalendar.time)
        edit_jam!!.hint = "${curCalendar.get(Calendar.HOUR_OF_DAY)}:00"

        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
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

        val time = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            myCalendar.set(Calendar.MINUTE, 0)

            if(myCalendar>=curCalendar) {
                edit_jam!!.setText("$hourOfDay:00")
            } else {
                Toast.makeText(this@ActivityPesanFutsal, "Invalid Time", Toast.LENGTH_SHORT).show()
            }
        }

        edit_tanggal!!.setOnClickListener{

                // TODO Auto-generated method stub
                DatePickerDialog(
                        this@ActivityPesanFutsal,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
        }

        edit_jam!!.setOnClickListener {
            TimePickerDialog(
                    this@ActivityPesanFutsal,
                    time,
                    myCalendar.get(Calendar.HOUR_OF_DAY),
                    myCalendar.get(Calendar.MINUTE),
                    true
                    ).show()
        }
        //=======================================Date===============================================

        //=====================================Recycler=============================================
        VenueArrayList = ArrayList()
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))
        VenueArrayList.add(ItemVenue("Registered Venue","on earth","qwerqwerq",R.drawable.lap1))

        VenueRecyclerView = findViewById(R.id.venue_recycler) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this)
        VenueRecyclerView.layoutManager = linearLayoutManager
        VenueRecyclerView.isFocusable = false

        val venueAdapter = ItemVenueAdapter(VenueArrayList,this)
        VenueRecyclerView.adapter = venueAdapter
        //=====================================Recycler=============================================

        //==========================================================================================
        //Floating Action Button
        val fab = findViewById(R.id.fab_venue_filter) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            if(filter.visibility== View.INVISIBLE){
                filter.visibility = View.VISIBLE
            }else{
                filter.visibility = View.INVISIBLE
            }

        }
        //Floating Action Button
        //==========================================================================================
    }

    private fun updateLabel() {
        val myFormat = "EEEE, dd/MMM/yyyy" //In which you need put here
        //val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        edit_tanggal!!.setText(sdf.format(myCalendar.time))
        //Log.d(TAG,"tgl = " + sdf.format(curCalendar.time))
    }
    private fun updateTime(){

    }

    private fun initDate(){
        curDay = SimpleDateFormat("dd", Locale.getDefault()).format(curCalendar.time)
        curMonth = SimpleDateFormat("MM", Locale.getDefault()).format(curCalendar.time)
        curYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(curCalendar.time)

        Log.d(TAG,"tgl = $curDay/$curMonth/$curYear")
    }
}
