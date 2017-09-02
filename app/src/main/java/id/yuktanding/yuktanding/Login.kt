package id.yuktanding.yuktanding

import android.app.Application
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
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


public class Login : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener{
    private var link: TextView? = null
    private var ib: ImageView? =null
    private var uie: TextInputEditText? = null
    private var uil: TextInputLayout? = null
    private var conlay: ConstraintLayout?= null
    private var btnSignin: Button? = null
    private var btnSignGoogle: SignInButton? = null
    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 100
    private val TAG = "Disini"
    private var backButtonCount = 0
    //phoneAuth
    private val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
    private val STATE_INITIALIZED = 1
    private val STATE_CODE_SENT = 2
    private val STATE_VERIFY_FAILED = 3
    private val STATE_VERIFY_SUCCESS = 4
    private val STATE_SIGNIN_FAILED = 5
    private val STATE_SIGNIN_SUCCESS = 6
    private var mVerificationInProgress = false
    private var mVerificationId=""
    private var mResendToken: PhoneAuthProvider.ForceResendingToken?= null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks?= null

    //==============================================================================================
    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_login_rev)
        Log.d(TAG, "setelah layout")

        //uil = findViewById(R.id.user_input_layout_log) as TextInputLayout
        //uie = findViewById(R.id.user_input_edit_log) as TextInputEditText
        conlay = findViewById(R.id.constraint_log) as ConstraintLayout
        btnSignin = findViewById(R.id.btn_Login) as Button
        btnSignGoogle = findViewById(R.id.g_signin_log) as SignInButton
        //link = findViewById(R.id.txt_tanya) as TextView
        Log.d(TAG, "setelah deklarasi tombol")

        val intente = Intent(this, MenuHome::class.java)

        Log.d(TAG, "setelah deklarasi intent")
        mAuth = FirebaseAuth.getInstance()
        initGso()

        btnSignGoogle!!.setOnClickListener {
            Log.d(TAG," Google sign in diklik")
            signIn()
        }
        btnSignin!!.setOnClickListener {
            Log.d(TAG," tombol masuk diklik")
            signOut() //haha

        }
    }
    //oncreate end
    //==============================================================================================

    //==============================================================================================
    //google sign in start
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

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        gantiTextSignIn(btnSignGoogle!!, "Google")
        // [END customize_button]
    }

    override fun onBackPressed() {
        if(backButtonCount >= 1)
        {
            var intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Log.d(TAG, "$backButtonCount")
        }
        else
        {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            backButtonCount++
            Log.d(TAG, "$backButtonCount")
        }
    }

    public override fun onStart() {
        super.onStart()

        //var opr = Auth.GOOGLE_SIGN_IN_API.silentSignIn(mGoogleApiClient) as OptionalPendingResult<GoogleSignInResult>
        //OptionalPendingResult<GoogleSignInResult> opr = mAuth!!.GoogleSignInApi.silentSignIn(mGoogleApiClient);


        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "onStart " + currentUser)

    }

    override fun onPause() {
        super.onPause()
        backButtonCount=0
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "dalem onAvtivityResult")
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.d(TAG, "Result $result")
            handleSignInResult(result)
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct : GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.idToken)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success")
                            val user = mAuth!!.getCurrentUser()
                            if(user!!.isEmailVerified==false) {
                                user.sendEmailVerification()
                            }
                            val intente = Intent(this@Login, MenuHome::class.java)
                            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intente)
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException())
                            //updateUI(null);
                        }

                    }
                })

    }
    // [END auth_with_google]

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
            val acct = result.signInAccount
            Log.d(TAG,"" +acct)
            Log.d(TAG, "" + acct!!.displayName)
            Log.d(TAG, "" + acct!!.account)
            Log.d(TAG, "" + acct!!.email)
            Log.d(TAG, "" + acct!!.id)
            Log.d(TAG,"mgoogleapi $mGoogleApiClient")
            val account = result.signInAccount
            if (account != null) {
                firebaseAuthWithGoogle(account)
            }
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false)
        }
    }

    // [START signIn]
    private fun signIn() {
        Log.d(TAG, "dalem signIn")
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signIn]

    private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback {

        }
    }

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
    //google sign in
    //==============================================================================================

    /*Unused Fun
    //==============================================================================================
    //text view link start
    /*
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
    */
    //text view link end
    //==============================================================================================

    //==============================================================================================
    //firebase createUser start
    /*
    private fun createUser(uEmail: String, uPass: String) {

        mAuth?.createUserWithEmailAndPassword(uEmail, uPass)
                ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        Log.d(TAG, "dalem createUser onComplete")
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user=mAuth?.getCurrentUser()
                            user?.sendEmailVerification()
                            Log.d(TAG, "verifikasi"+user)
//                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure")
//                            updateUI(null)
                        }
                    }
                })
    }
    */
    //firebase createUser end
    //==============================================================================================

    //==============================================================================================
    //picasso change background start
    /*
    fun picas(imgURL: String, uview: Drawable) {
        Log.d(TAG, "sebelum Picasso")
        Picasso.with(this)
                .load(imgURL)
                .placeholder(R.color.yukTandingOren)
                .error(R.color.yukTandingOren)
                .into()
        Log.d(TAG, "setelah Picasso")
    }
*/
    //picasso change background end
    //==============================================================================================

    //==============================================================================================
    //kitti start
    /*
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
    */
    //kitti end
    //==============================================================================================
    */
}



