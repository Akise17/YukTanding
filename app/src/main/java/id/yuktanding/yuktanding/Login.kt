package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.squareup.picasso.Picasso
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import android.text.method.LinkMovementMethod
import android.text.Spanned
import android.text.TextPaint
import android.content.Intent
import android.view.View


class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    private var link: TextView?=null
    private var span: SpannableString?=null
    private var uie: TextInputEditText?=null
    private var uil: TextInputLayout?=null
    private var btnSignin: Button?=null
    private var btnSignGoogle: Button?=null
    private var mAuth: FirebaseAuth? = null
    //private val mAuthListener: FirebaseAuth.AuthStateListener? = null
    val TAG = "Disini"

    //==============================================================================================
    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")

        setContentView(R.layout.activity_login)
        Log.d(TAG,"setelah layout")

        //uil = findViewById(R.id.user_input_layout_log) as TextInputLayout
        //uie = findViewById(R.id.user_input_edit_log) as TextInputEditText
        ib = findViewById(R.id.image_background_log) as ImageView
        link = findViewById(R.id.txt_tanya) as TextView
        val intent = Intent(this,SignUp::class.java)

        //var spann = SpannableString(getText(R.id.txt_tanya))

        val ss = SpannableString("Belum punya akun? Sign Up")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View?) {
                //intent
                Log.d(TAG,"diklik")
                startActivity(intent)
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 18, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        link!!.setText(ss)
        link!!.movementMethod = LinkMovementMethod.getInstance()

        //btnSignin = findViewById(R.id.btn_Login) as Button
        //btnSignGoogle = findViewById(R.id.btn_Google) as Button

        //kitti("google.com")
        backgrnd()

        mAuth = FirebaseAuth.getInstance();
        //createUser("ekky@yuktanding.id","sabuncolek")

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG,"onStart"+currentUser)
    }

    fun backgrnd() {
        Log.d(TAG, "sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lap.jpg")
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib)
        Log.d(TAG, "setelah Picasso")
    }

    fun kitti(URL: String) {
        Fuel.get(URL).response { request, response, result ->
            println(request)
            println(response)
            val (bytes, error) = result
            Log.d(TAG, "dalam kitti " + response)
            if (bytes != null) {
                println(bytes)
            }
        }
    }
}


