package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import com.squareup.picasso.Picasso

class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    public val logg= "Disini"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logg,"onCreate")
        setContentView(R.layout.activity_login)

        ib = findViewById(R.id.image_background) as ImageView

        Picasso.with(this).load("https://yuktanding.id/img/lap.jpg").into(ib)
    }
}
