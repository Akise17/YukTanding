package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.AuthCredential
import com.squareup.picasso.Picasso
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.android.gms.auth.api.signin.GoogleSignInOptions




class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    private var uie: TextInputEditText?=null
    private var uil: TextInputLayout?=null
    private var btnSignin: Button?=null
    private var btnSignGoogle: Button?=null
    private var mAuth: FirebaseAuth? = null
    private val mAuthListener: FirebaseAuth.AuthStateListener? = null
    val TAG = "Disini"
    val email = null
    val password = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")

        //======================================================
        //firebase

        //firebase
        // ======================================================

        setContentView(R.layout.activity_login)
        Log.d(TAG,"setelah layout")

        uil = findViewById(R.id.user_input_layout) as TextInputLayout
        uie = findViewById(R.id.user_input_edit) as TextInputEditText
        ib = findViewById(R.id.image_background) as ImageView
        btnSignin = findViewById(R.id.btn_Login) as Button
        btnSignGoogle = findViewById(R.id.btn_Google) as Button

        Log.d(TAG,"sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lap.jpg")
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib);
        Log.d(TAG,"setelah Picasso")
    }


}


