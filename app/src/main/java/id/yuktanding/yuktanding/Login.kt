package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    private var uie: TextInputEditText?=null
    private var uil: TextInputLayout?=null
    val logg= "Disini"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logg,"onCreate")
        setContentView(R.layout.activity_login)
        Log.d(logg,"setelah layout")

        uil = findViewById(R.id.user_input_layout) as TextInputLayout
        uie = findViewById(R.id.user_input_edit) as TextInputEditText
        ib = findViewById(R.id.image_background) as ImageView

        Log.d(logg,"sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lap.jpg")
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib);
        Log.d(logg,"setelah Picasso")
    }
}
