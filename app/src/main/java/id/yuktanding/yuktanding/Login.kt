package id.yuktanding.yuktanding

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.Window
import android.widget.ImageView
import com.squareup.picasso.Picasso

class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    private var uin: TextInputEditText?=null
    val logg= "Disini"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logg,"onCreate")
        setContentView(R.layout.activity_login)

        uin = findViewById(R.id.user_input_edit) as TextInputEditText
        ib = findViewById(R.id.image_background) as ImageView

        Picasso.with(this).load("https://yuktanding.id/img/lap.jpg").into(ib)

    }
}
