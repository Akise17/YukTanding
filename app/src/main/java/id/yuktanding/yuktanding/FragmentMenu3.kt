package id.yuktanding.yuktanding


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FragmentMenu3 : Fragment() {

    internal lateinit var timArrayList: ArrayList<ItemTim>
    internal lateinit var timRecyclerView: RecyclerView
    private val TAG = "Disini fragment 3 "

    lateinit var databaseTim: FirebaseDatabase
    lateinit var myRef: DatabaseReference

    lateinit var user: FirebaseUser

    var userUID: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_menu_fragment3, container, false)

        user = FirebaseAuth.getInstance().currentUser!!
        if (user != null) userUID = user.uid

        myRef = databaseTim.getReference().child(userUID)

        myRef.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        Log.d(TAG, "onCreateView")

        timArrayList = ArrayList()

        timArrayList.add(ItemTim("Lontong FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))

        timRecyclerView = view.findViewById(R.id.tim_recyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        timRecyclerView.layoutManager = linearLayoutManager

        val itemAdapter = ItemTimAdapter(timArrayList, context)
        timRecyclerView.adapter = itemAdapter

        return view
    }

}// Required empty public constructor
