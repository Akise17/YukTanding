package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.squareup.picasso.Picasso
import com.google.firebase.auth.FirebaseAuth
import android.text.method.LinkMovementMethod
import android.text.Spanned
import android.text.TextPaint
import android.content.Intent
import android.view.View
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status

class Login : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private var ib: ImageView? = null
    private var link: TextView? = null
    private var uie: TextInputEditText? = null
    private var uil: TextInputLayout? = null
    private var btnSignin: Button? = null
    private var btnSignGoogle: SignInButton? = null
    var mAuth: FirebaseAuth? = null
    var mGoogleApiClient: GoogleApiClient? = null
    val RC_SIGN_IN = 100
    //private val mAuthListener: FirebaseAuth.AuthStateListener? = null
    val TAG = "Disini"

    //==============================================================================================
    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_login)
        Log.d(TAG, "setelah layout")

        //uil = findViewById(R.id.user_input_layout_log) as TextInputLayout
        //uie = findViewById(R.id.user_input_edit_log) as TextInputEditText
        btnSignin = findViewById(R.id.btn_Login) as Button
        btnSignGoogle = findViewById(R.id.g_signin_log) as SignInButton
        ib = findViewById(R.id.image_background_log) as ImageView
        link = findViewById(R.id.txt_tanya) as TextView
        Log.d(TAG, "setelah deklarasi tombol")

        val intent = Intent(this, SignUp::class.java)

        Log.d(TAG, "setelah deklarasi intent")
        mAuth = FirebaseAuth.getInstance()
        initGso()

        btnSignGoogle!!.setOnClickListener {
            signIn()
        }

        //spannable string (link signup)
        span()
        //kitti("google.com")
        //backgrnd()
        //mAuth!!.signOut()
        //createUser("ekky@yuktanding.id","sabuncolek")
    }
    //oncreate end
    //==============================================================================================

    private fun initGso() {
        Log.d(TAG, "sebelum gso")
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
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

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        gantiTextSignIn(btnSignGoogle!!, "Google")
        // [END customize_button]

    }

    public override fun onStart() {
        super.onStart()

        //var opr = Auth.GOOGLE_SIGN_IN_API.silentSignIn(mGoogleApiClient) as OptionalPendingResult<GoogleSignInResult>
        //OptionalPendingResult<GoogleSignInResult> opr = mAuth!!.GoogleSignInApi.silentSignIn(mGoogleApiClient);


        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "onStart" + currentUser)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            Log.d(TAG, "" + acct!!.displayName)
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct!!.displayName))
            //updateUI(true)
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false)
        }
    }

    // [START signIn]
    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signIn]

    // [START signOut]
    private fun signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                object : ResultCallback<Status> {
                    override fun onResult(status: Status) {
                        // [START_EXCLUDE]
                        //updateUI(false)
                        // [END_EXCLUDE]
                    }
                })
    }
    // [END signOut]

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    protected fun gantiTextSignIn(signInButton: SignInButton, buttonText: String) {
        // Find the TextView that is inside of the SignInButton and set its text
        for (i in 0..signInButton.childCount - 1) {
            val v = signInButton.getChildAt(i)

            if (v is TextView) {
                v.text = buttonText
                return
            }
        }
    }


    private fun span() {
        val ss = SpannableString("Belum punya akun? Sign Up")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View?) {
                //intent
                Log.d(TAG, "diklik")
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 18, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        link!!.setText(ss)
        link!!.movementMethod = LinkMovementMethod.getInstance()
    }

    fun backgrnd() {
        Log.d(TAG, "sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lapOri.jpg")
                .placeholder(R.color.yukTandingOren)
                .error(R.color.yukTandingOren)
                .into(ib)
        Log.d(TAG, "setelah Picasso")
    }

    fun kitti(URL: String) {
        Fuel.get(URL).response { request, response, result ->
            println(request)
            println(response)
            val (bytes, error) = result
            Log.d(TAG, "dalam kitti " + response)
            if (bytes != null) {
                println(bytes)
            }
        }
    }
}



