package id.yuktanding.yuktanding


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso


class FragmentMenu2 : Fragment() {

    private val TAG = "Disini fragment2  "
    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    internal lateinit var foto: ImageView
    internal lateinit var nama: TextView
    private val RC_SIGN_IN = 100
    private var evar = "" //string buat email
    private var nvar = "" // string buat nama
    private var ivar = "" // string buat url foto

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view= inflater!!.inflate(R.layout.fragment_menu_fragment2, container, false)
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance() //Buat tau data user yang udah login
        getUserInf() //buat ngambil data email,nama,url foto
        Log.d(TAG,"setelah getUserInf")
        initGso() //inisialisasi google (TODO gw gak tau ini perlu dipanggil di setiap activity apa enggak)
        Log.d(TAG,"setelah init gso")
        foto= view.findViewById(R.id.img_profil) as ImageView
        nama= view.findViewById(R.id.nama_profil) as TextView
        nama.setText(nvar)
        Picasso.with(context)
                .load(ivar)
                .into(foto)
        return view
    }


    private fun initGso() {
        Log.d(TAG, "sebelum gso")
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .requestId()
                .build()
        // [END configure_signin]

        Log.d(TAG, "sebelum mGoogle")
        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = GoogleApiClient.Builder(context)
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)!!
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        mGoogleApiClient!!.connect()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)

        Log.d(TAG, "setelah mGoogle $mGoogleApiClient")
        // [END build_client]
    }

    private fun getUserInf(){
        val currentUser = mAuth?.getCurrentUser()
        Log.d(TAG, "Firebase " + currentUser)
        Log.d(TAG, "Firebase " + currentUser!!.email)
        Log.d(TAG, "Firebase " + currentUser.displayName)
        Log.d(TAG, "Firebase " + currentUser.photoUrl)
        Log.d(TAG, "Firebase " + currentUser.phoneNumber)
        evar = currentUser.email!!
        nvar = currentUser.displayName!!
        ivar = currentUser.photoUrl.toString()
    }
}// Required empty public constructor
