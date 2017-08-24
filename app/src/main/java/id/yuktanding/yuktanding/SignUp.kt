package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso



class SignUp : AppCompatActivity() {
    private val TAG = "Disini signup"

    private var ib: ImageView?=null
    private var sb: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)
        ib = findViewById(R.id.image_background_sign) as ImageView
        sb = findViewById(R.id.btn_Login_sign) as Button

        backgrnd() //ganti background
    }

    fun backgrnd(){
        Log.d(TAG,"sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/sign.jpg") //Created by Pressfoto - Freepik.com
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib)
        Log.d(TAG,"setelah Picasso")
    }
}
