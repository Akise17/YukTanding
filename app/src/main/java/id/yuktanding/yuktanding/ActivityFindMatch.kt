package id.yuktanding.yuktanding

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class ActivityFindMatch : AppCompatActivity() {

    private val TAG = "Disini actfindmatch "

    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 100
    internal lateinit var MatchArrayList: ArrayList<ItemMatch>
    internal lateinit var MatchRecyclerView: RecyclerView
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_match)

        Log.d(TAG,"setelah setContentView")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val btn_create = findViewById<Button>(R.id.btn_fm_create)

        MatchRecyclerView = findViewById(R.id.match_recycler)

        //initGso()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG,"setelah support")

        //=====================================FireStore============================================
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        //val docRef = db.collection("cities").document("SF")
        Log.d(TAG,"dalem db ${db}")
        Log.d(TAG,"dalem db ${db.app.uid}")
        MatchArrayList = ArrayList()

        db.collection("OnGoingMatch")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                            if(document.get("n_biru").toString()==""){
                                MatchArrayList.add(ItemMatch(
                                        "Available",
                                        "Click to Join room",
                                        "https://yuktanding.id/img/bola.png",
                                        document.get("n_merah").toString(),
                                        document.get("d_merah").toString(),
                                        document.get("img_merah").toString(),
                                        "","","",""))
                            }else {
                                MatchArrayList.add(ItemMatch(
                                        document.get("n_biru").toString(),
                                        document.get("d_biru").toString(),
                                        document.get("img_biru").toString(),
                                        document.get("n_merah").toString(),
                                        document.get("d_merah").toString(),
                                        document.get("img_merah").toString(),
                                        "", "", "", ""))
                            }
                        }
                        updateMatch()
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                    }
                }

        Log.d(TAG,"setelah db")
        //=====================================FireStore============================================

        btn_create.setOnClickListener {
            val intente = Intent(this@ActivityFindMatch, ActivityPesanFutsal::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)
        }
    }

    fun updateMatch(){
        Log.d(TAG,"updateMatch")
        val matchAdapter = ItemMatchAdapter(MatchArrayList,this)
        Log.d(TAG,"updateMatch")
        MatchRecyclerView.layoutManager = linearLayoutManager
        Log.d(TAG,"updateMatch")
        MatchRecyclerView.isFocusable=false
        MatchRecyclerView.adapter = matchAdapter
        Log.d(TAG,"updateMatch")
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
