package id.yuktanding.yuktanding

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class ActivityCariLawan : AppCompatActivity() {

    private var mTextMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_lawan)

        mTextMessage = findViewById<TextView>(R.id.message)
    }

}
