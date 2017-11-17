package id.yuktanding.yuktanding

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ActivitySplashScreen : AppCompatActivity() ,GoogleApiClient.OnConnectionFailedListener{
    var mAuth: FirebaseAuth? = null
    var mGoogleApiClient: GoogleApiClient? = null
    private var TAG= "disini launcher "
    var curUser: FirebaseUser?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Log.d(TAG,"onCreate sebelum layout")
        //setContentView(R.layout.activity_launcher)
        Log.d(TAG,"onCreate setelah layout")
        mAuth = FirebaseAuth.getInstance()
        initGso()

        Handler().postDelayed(object : Runnable {
            override fun run() {
                redir()
                this.finish()
            }

            private fun finish() {

            }

        }, splashInterval.toLong())

    }

    companion object {

        private val splashInterval = 2000
    }

    public override fun onStart() {
        super.onStart()
        //var opr = Auth.GOOGLE_SIGN_IN_API.silentSignIn(mGoogleApiClient) as OptionalPendingResult<GoogleSignInResult>
        //OptionalPendingResult<GoogleSignInResult> opr = mAuth!!.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        // Check if user is signed in (non-null) and update UI accordingly.
        curUser = mAuth!!.currentUser
        Log.d(TAG, "onStart " + curUser)

    }

    private fun redir(){
        if(curUser==null){
            Log.d(TAG, "onStart belum login")
            val intente = Intent(this@ActivitySplashScreen, Login::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)
        }
        else{
            Log.d(TAG, "onStart sudah login")
            val intente = Intent(this@ActivitySplashScreen, ActivityMain::class.java)
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
