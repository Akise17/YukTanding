package id.yuktanding.yuktanding

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso

class ActivityMain : AppCompatActivity() ,GoogleApiClient.OnConnectionFailedListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mViewPager: ViewPager? = null

    //variable firebase [Start]
    private val TAG = "Disini Main Home  "
    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 100
    //variable fire base [End]

    private var backButtonCount = 0 //buat toast press again to exit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.d(TAG,"setelah Layout")

        mAuth = FirebaseAuth.getInstance() //Buat tau data user yang udah login
        initGso() //inisialisasi google (TODO gw gak tau ini perlu dipanggil di setiap activity apa enggak)
        Log.d(TAG,"setelah init gso")

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(null)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        Log.d(TAG,"setelah toolbar dan pagerAdapter")

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = mSectionsPagerAdapter
        Log.d(TAG,"setelah viewPager")

        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)
        Log.d(TAG,"setelah tab layout")

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.menu.getItem(1).isChecked = true //posisi nav bar aktif
        Log.d(TAG," setelah bootomnav")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
            if(backButtonCount >= 1) // TODO saat toast ilang backButtonCountnya dijadiin 0 lagi
            {
                var intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                Log.d(TAG, "$backButtonCount")
            }
            else
            {
                // TODO kelemahannya saat toast udah ilang, dipencet sekali langsung keluar (lemes deh)
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
                backButtonCount++
                Log.d(TAG, "$backButtonCount")
            }
    }

    override fun onPause() {
        super.onPause()
        backButtonCount=0
    }

    override fun onRestart() {
        super.onRestart()
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.menu.getItem(1).isChecked = true
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_pesanLapangan -> {
                Log.d("yukTanding", "Navigation Pesan Lapangan Main")
                val intent = Intent(this, ActivityPesanLapangan::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                Log.d("yukTanding", "Navigation Main Menu Main")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cariLawan -> {
                Log.d("yukTanding", "Navigation Cari Lawan Main")
                val intent = Intent(this, ActivityCariLawan::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when(id){
            R.id.action_buatTim -> {
                Log.d("yukTanding", "BuatTim")
                val intent = Intent(this, ActivityBuatTim::class.java)
                startActivity(intent)
            }
            R.id.action_logout -> {signOut()}
        }

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_main2, container, false)
            val textView = rootView.findViewById(R.id.section_label) as TextView
            textView.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            when (position){
                0 -> return FragmentMenu1()
                1 -> return FragmentMenu2()
                2 -> return FragmentMenu3()
            }
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Jadwal"
                1 -> return "Profil"
                2 -> return "TIM"
            }
            return null
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
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)!!
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        mGoogleApiClient!!.connect()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)

        Log.d(TAG, "setelah mGoogle $mGoogleApiClient")
        // [END build_client]
    }

    private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "Firebase " + currentUser)
        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback {
            Log.d(TAG, "dalem callback " )
            val intente = Intent(this@ActivityMain, Login::class.java)
            intente.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intente)
        }
    }
}
