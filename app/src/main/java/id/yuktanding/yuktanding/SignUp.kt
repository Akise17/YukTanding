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
import com.google.firebase.auth.FirebaseUser


class SignUp : AppCompatActivity() {
    private val TAG = "Disini signup"

    private var ib: ImageView? = null
    private var sb: Button? = null
    private var mAuth: FirebaseAuth? = null
    private val getUser: FirebaseUser? = mAuth?.getCurrentUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)
        ib = findViewById(R.id.image_background_sign) as ImageView
        sb = findViewById(R.id.btn_Login_sign) as Button

        mAuth = FirebaseAuth.getInstance();

        kitti("http://yuktanding.id/img/test.html")
        backgrnd() //ganti background
        //createUser("ahmad.fauzan1603@gmail.com", "12345678")
    }

    fun backgrnd() {
        Log.d(TAG, "sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/sign.jpg") //Created by Pressfoto - Freepik.com
                .placeholder(R.drawable.lap1)
                .error(R.drawable.lap1)
                .into(ib)
        Log.d(TAG, "setelah Picasso")
    }

    private fun createUser(uEmail: String, uPass: String) {

        mAuth?.createUserWithEmailAndPassword(uEmail, uPass)
                ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        Log.d(TAG, "dalem createUseronComplete")
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user=mAuth?.getCurrentUser()
                            user?.sendEmailVerification()
                            Log.d(TAG, "verifikasi"+user)
//                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure")
//                            updateUI(null)
                        }
                    }
                })
    }

    fun kitti(URL: String) {
        //======================================================
        //kittinunf
        Fuel.get(URL).response { request, response, result ->
            println(request)
            println(response)
            val (bytes, error) = result
            Log.d(TAG, "dalam kitti " + response)
            if (bytes != null) {
                println(bytes)
            }
        }
        //kittinunf
        //======================================================
    }

}
