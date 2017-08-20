package id.yuktanding.yuktanding

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log

class PesanLapangan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_lapangan)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.menu.getItem(0).isChecked = true

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_pesanLapangan -> {
                Log.d("BottomBar", "Navigation Pesan Lapangan")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                Log.d("BottomBar", "Navigation Main Menu")
                val intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cariLawan -> {
                Log.d("BottomBar", "Navigation Cari Lawan")
                val intent = Intent(this, CariLawan::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
