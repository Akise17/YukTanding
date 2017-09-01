package id.yuktanding.yuktanding

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth

class Launcher : AppCompatActivity() ,GoogleApiClient.OnConnectionFailedListener{

    var mAuth: FirebaseAuth? = null
    var mGoogleApiClient: GoogleApiClient? = null
    private var TAG= "disini launcher "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate sebelum layout")
        //setContentView(R.layout.activity_launcher)
        Log.d(TAG,"onCreate setelah layout")
        mAuth = FirebaseAuth.getInstance()
        initGso()
    }

    public override fun onStart() {
        super.onStart()

        //var opr = Auth.GOOGLE_SIGN_IN_API.silentSignIn(mGoogleApiClient) as OptionalPendingResult<GoogleSignInResult>
        //OptionalPendingResult<GoogleSignInResult> opr = mAuth!!.GoogleSignInApi.silentSignIn(mGoogleApiClient);


        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "onStart " + currentUser)
        if(currentUser==null){
            Log.d(TAG, "onStart belum login")
            val intente = Intent(this@Launcher, Login::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)
        }
        else{
            Log.d(TAG, "onStart sudah login")
            val intente = Intent(this@Launcher, MenuHome::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)
        }

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
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)!!
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        // [END build_client]
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}
