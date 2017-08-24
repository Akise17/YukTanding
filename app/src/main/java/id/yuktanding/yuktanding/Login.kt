package id.yuktanding.yuktanding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.github.kittinunf.fuel.Fuel
import com.squareup.picasso.Picasso
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class Login : AppCompatActivity() {
    private var ib: ImageView?=null
    private var uie: TextInputEditText?=null
    private var uil: TextInputLayout?=null
    private var btnSignin: Button?=null
    private var btnSignGoogle: Button?=null
    private var mAuth: FirebaseAuth? = null
    private val mAuthListener: FirebaseAuth.AuthStateListener? = null
    val TAG = "Disini"

    //==============================================================================================
    //onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")

        //======================================================
        //kittinunf
        Fuel.get("http://yuktanding.id/img/test.html").response { request, response, result ->
            println(request)
            println(response)
            val (bytes, error) = result
            Log.d(TAG,"dalam kitti "+response)
            if (bytes != null) {
                println(bytes)
            }
        }
        Log.d(TAG,"setelah kitti")
        //kittinunf
        //======================================================



        //======================================================
        //firebase
        mAuth = FirebaseAuth.getInstance();

        Log.d(TAG,"sebelum mAuth")
        //createUser("ekky@yuktanding.id","sabuncolek")

        //firebase
        // ======================================================

        setContentView(R.layout.activity_login)
        Log.d(TAG,"setelah layout")

        uil = findViewById(R.id.user_input_layout_log) as TextInputLayout
        uie = findViewById(R.id.user_input_edit_log) as TextInputEditText
        ib = findViewById(R.id.image_background_log) as ImageView
        btnSignin = findViewById(R.id.btn_Login) as Button
        //btnSignGoogle = findViewById(R.id.btn_Google) as Button

        Log.d(TAG,"sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lap.jpg")
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib);
        Log.d(TAG,"setelah Picasso")
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG,"onStart"+currentUser)
    }

    private fun createUser(uEmail:String, uPass:String) {

        mAuth?.createUserWithEmailAndPassword(uEmail, uPass)
                ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        Log.d(TAG,"dalem onClomplete")
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = mAuth?.getCurrentUser()
//                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException())
//                            updateUI(null)
                        }

                        // ...
                    }
                })

    }
}


