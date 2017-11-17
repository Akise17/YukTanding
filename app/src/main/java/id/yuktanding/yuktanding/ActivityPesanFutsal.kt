package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.app.DatePickerDialog
import android.util.Log
import java.util.*
import java.text.SimpleDateFormat
import android.app.TimePickerDialog
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth


class ActivityPesanFutsal : AppCompatActivity() {

    private var edit_tanggal: EditText?=null
    private var edit_jam: EditText?=null
    private var loc_spinner: Spinner?=null
    private var filter: RelativeLayout?=null
    private var mAuth: FirebaseAuth? = null
    private val RC_SIGN_IN = 100
    private var mGoogleApiClient: GoogleApiClient? = null
    internal lateinit var VenueArrayList: ArrayList<ItemVenue>
    internal lateinit var VenueRecyclerView: RecyclerView
    private var myCalendar = Calendar.getInstance()
    private var curCalendar = Calendar.getInstance()
    private var curDay=""
    private var curMonth=""
    private var curYear=""
    private val TAG = "Disini actfutsal "
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_futsal)

        Log.d(TAG,"setelah setContentView")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val filter = findViewById<RelativeLayout>(R.id.venue_filter)

        VenueRecyclerView = findViewById(R.id.venue_recycler)

        Log.d(TAG,"setelah findview")

        initGso()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG,"setelah support")


        //=======================================Spinner============================================
        loc_spinner=findViewById(R.id.pilih_lok)
        var loc_item = ArrayList<String>()

        //=======================================Spinner============================================
        
        //=====================================FireStore============================================
        val db :FirebaseFirestore = FirebaseFirestore.getInstance()

        //val docRef = db.collection("cities").document("SF")
        Log.d(TAG,"dalem db ${db}")
        Log.d(TAG,"dalem db ${db.app.uid}")
        VenueArrayList = ArrayList()

        db.collection("Futsal")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                            Log.d(TAG, "data "+ document.get("Addr"))
                            Log.d(TAG, "data "+ document.get("Field"))
                            VenueArrayList.add(ItemVenue(document.id,document.get("Addr").toString(),"qwerqwerq",document.get("img").toString()))
                        }
                        updateVenue()
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                    }
                }

        Log.d(TAG,"setelah db")
        //=====================================FireStore============================================

        //=======================================Date===============================================
        edit_tanggal = findViewById(R.id.edit_tgl)
        edit_jam = findViewById(R.id.edit_jam)

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



        //=====================================Recycler=============================================

        //==========================================================================================
        //Floating Action Button
        val btn_filter_ok = findViewById<Button>(R.id.btn_apf_filter)
        val fab = findViewById<FloatingActionButton>(R.id.fab_venue_filter)
        btn_filter_ok.setOnClickListener {
            fab.show()
            filter.visibility = View.INVISIBLE
        }
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            if(filter.visibility== View.INVISIBLE){
                filter.visibility = View.VISIBLE
                fab.hide()
            }
        }
        //Floating Action Button
        //==========================================================================================

    }
    fun updateVenue(){
        val venueAdapter = ItemVenueAdapter(VenueArrayList,this)
        VenueRecyclerView.layoutManager = linearLayoutManager
        VenueRecyclerView.isFocusable = false
        VenueRecyclerView.adapter = venueAdapter
    }

    private fun updateLabel() {
        val myFormat = "EEEE, dd/MMM/yyyy" //In which you need put here
        //val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        edit_tanggal!!.setText(sdf.format(myCalendar.time))
        //Log.d(TAG,"tgl = " + sdf.format(curCalendar.time))
    }

    private fun initDate(){
        curDay = SimpleDateFormat("dd", Locale.getDefault()).format(curCalendar.time)
        curMonth = SimpleDateFormat("MM", Locale.getDefault()).format(curCalendar.time)
        curYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(curCalendar.time)

        Log.d(TAG,"tgl = $curDay/$curMonth/$curYear")
    }

    private fun initGso() {
        Log.d(TAG, "sebelum gso")
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .requestId()
                .build()
        // [END configure_signin]

        Log.d(TAG, "sebelum mGoogle")
        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = GoogleApiClient.Builder(this)
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)!!
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        mGoogleApiClient!!.connect()
        Log.d(TAG, "sebelum mAuth")
        mAuth = FirebaseAuth.getInstance()
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)

        Log.d(TAG, "setelah mGoogle $mGoogleApiClient")
        // [END build_client]
    }
}
