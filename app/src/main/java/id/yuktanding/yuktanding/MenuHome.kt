package id.yuktanding.yuktanding

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

class MenuHome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG = "Disini home "
    private var mAuth: FirebaseAuth? = null
    private var n_draw: TextView? =null
    private var e_draw: TextView? = null
    private var i_draw: ImageView?= null
    var evar=""
    var nvar=""
    var ivar=""


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
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "Firebase " + currentUser)
        Log.d(TAG, "Firebase " + currentUser!!.email)
        evar= currentUser.email!!
        Log.d(TAG, "Firebase " + currentUser!!.displayName)
        nvar= currentUser.displayName!!
        Log.d(TAG, "Firebase " + currentUser!!.photoUrl)
        ivar= currentUser.photoUrl.toString()
        Log.d(TAG, "Firebase " + currentUser!!.isEmailVerified)

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

//        e_draw!!.setText(currentUser.email)
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
        e_draw= findViewById(R.id.email_draw) as TextView
        n_draw= findViewById(R.id.nama_draw) as TextView
        i_draw= findViewById(R.id.imageView_draw) as ImageView
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

        when(id){
            R.id.nav_camera -> Log.d(TAG,"nav kamera")
            R.id.nav_slideshow -> Log.d(TAG,"nav slide")
            R.id.nav_manage -> Log.d(TAG,"nav slide")
            R.id.nav_share -> Log.d(TAG,"nav slide")
            R.id.nav_send -> Log.d(TAG,"nav slide")
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
