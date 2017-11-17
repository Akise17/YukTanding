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
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast


class SignUp : AppCompatActivity() {
    private val TAG = "Disini signup"
    private var ib: ImageView?=null
    private var sb: Button?=null
    private var pwEditLay: TextInputLayout?=null
    private var mAuth: FirebaseAuth? = null
    private var passSame=null
    //private val getUser: FirebaseUser? = mAuth?.getCurrentUser()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)
        ib = findViewById<ImageView>(R.id.image_background_sign)
        sb = findViewById<Button>(R.id.btn_sign_up)
        pwEditLay = findViewById<TextInputLayout>(R.id.pass_in_sign)
        var emailIn = findViewById<TextInputEditText>(R.id.email_input_edit_sign)
        var pwIn1 = findViewById<TextInputEditText>(R.id.pass_input_edit_sign)
        var pwIn2 = findViewById<TextInputEditText>(R.id.pass_re_edit_sign)
        mAuth = FirebaseAuth.getInstance()

        kitti("http://yuktanding.id/img/test.html")
        //backgrnd() //ganti background
        //setupFloatingLabelError()

        sb!!.setOnClickListener{
            Log.d(TAG, "signup diklik")
            var e = emailIn.text
            var p1 = pwIn1.text
            var p2 = pwIn2.text
            Log.d(TAG, "email "+e)
            Log.d(TAG, "pass1 "+p1)
            Log.d(TAG, "pass2 "+p2)
            if(p1.toString()==p2.toString()) {
                createUser(e.toString(), p1.toString())
                Log.d(TAG, "regis")
            }
            else
            {
                Log.d(TAG, "gagal regis")
            }
            //prosesDaftar(e,p1,p2)
        }

    }

    //private val tbTitles = arrayOf(resources.getText(R.string.vp_locations), resources.getText(R.string.vp_client_area), resources.getText(R.string.vp_menu))

    private fun prosesDaftar(email: String, pass1: String, pass2: String)
    {
        if(pass1==pass2) {
            createUser(email, pass1)
            Log.d(TAG, "regis")

        }
        else
        {
            Log.d(TAG, "gagal regis")
        }
    }

    private fun setupFloatingLabelError() {
        val floatingUsernameLabel = findViewById<TextInputLayout>(R.id.email_input_layout_sign)

        floatingUsernameLabel.editText!!.addTextChangedListener(object : TextWatcher {
            // ...
            override fun onTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
                if (text.length > 0 && text.length <= 4) {
                    floatingUsernameLabel.error = getString(R.string.mohon_email)
                    floatingUsernameLabel.isErrorEnabled = true

                    Log.d(TAG, "dalem ontextchanged")
                } else {
                    floatingUsernameLabel.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }


    fun backgrnd() {
        Log.d(TAG, "sebelum Picasso")
        Picasso.with(this)
                .load("https://yuktanding.id/img/lapDark.jpg")
                .placeholder(R.color.yukTandingOren)
                .error(R.color.yukTandingOren)
                .into(ib)
        Log.d(TAG, "setelah Picasso")
    }

    private fun createUser(uEmail: String, uPass: String) {

        mAuth?.createUserWithEmailAndPassword(uEmail, uPass)
                ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        Log.d(TAG, "dalem createUser onComplete")
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
