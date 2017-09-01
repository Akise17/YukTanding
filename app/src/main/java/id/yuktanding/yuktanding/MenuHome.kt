package id.yuktanding.yuktanding

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.squareup.picasso.Picasso
import com.google.firebase.auth.FirebaseAuth

class MenuHome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    val TAG = "Disini home "
    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    val RC_SIGN_IN = 100
    private var n_draw: TextView? = null
    private var e_draw: TextView? = null
    private var i_draw: ImageView? = null
    var evar = ""
    var nvar = ""
    var ivar = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_menu_home)
        Log.d(TAG, "Layout")
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        Log.d(TAG, "Init")
        setSupportActionBar(toolbar)
        Log.d(TAG, "mAuth")

        mAuth = FirebaseAuth.getInstance()
        val acg = intent.getStringExtra("KEY")
        Log.d(TAG, "intent " + acg)

        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "Firebase " + currentUser)
        Log.d(TAG, "Firebase " + currentUser!!.email)
        Log.d(TAG, "Firebase " + currentUser!!.displayName)
        Log.d(TAG, "Firebase " + currentUser!!.photoUrl)
        Log.d(TAG, "Firebase " + currentUser!!.phoneNumber)
        evar = currentUser.email!!
        nvar = currentUser.displayName!!
        ivar = currentUser.photoUrl.toString()

        initGso()
        //==========================================================================================
        //Floating Action Button
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        //Floating Action Button
        //==========================================================================================

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "dalem onAvtivityResult")
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.d(TAG, "Result $result")
            if (result.isSuccess) {
                // Signed in successfully, show authenticated UI.
                val acct = result.signInAccount
                Log.d(TAG, "" + acct)
                Log.d(TAG, "" + acct!!.displayName)
                Log.d(TAG, "" + acct!!.account)
                Log.d(TAG, "" + acct!!.email)
                Log.d(TAG, "" + acct!!.id)
                Log.d(TAG,"mgoogleapi $mGoogleApiClient")
                //Auth.GoogleSignInApi.silentSignIn(result)
            }
            else{
                Log.d(TAG, "Failed")
            }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        e_draw = findViewById(R.id.email_draw) as TextView
        n_draw = findViewById(R.id.nama_draw) as TextView
        i_draw = findViewById(R.id.imageView_draw) as ImageView
        e_draw!!.setText(evar)
        n_draw!!.setText(nvar)
        Picasso.with(this)
                .load(ivar)
                .placeholder(R.color.yukTandingOren)
                .error(R.color.yukTandingOren)
                .into(i_draw)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_squad -> Log.d(TAG, "nav kamera")
            R.id.nav_history -> Log.d(TAG, "nav slide")
            R.id.nav_help -> Log.d(TAG, "nav manage")
            R.id.nav_setting -> Log.d(TAG, "nav priv")
            R.id.nav_signout -> signOut()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
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

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)

        Log.d(TAG, "setelah mGoogle $mGoogleApiClient")
        // [END build_client]
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "Firebase " + currentUser)
        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback {
            Log.d(TAG, "dalem callback " )
            val intente = Intent(this@MenuHome, Login::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)

//            object : ResultCallback<Status> {
//                override fun onResult(status: Status) {
//                    Log.d(TAG, "dalem onresult $status " )
//                    // [START_EXCLUDE]
//                    if (status.isSuccess) {
//
//                    }
//                    // [END_EXCLUDE]
//                }
//            }
        }
    }
}