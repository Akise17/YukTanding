package id.yuktanding.yuktanding


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import java.util.ArrayList

class FragmentMenu1 : Fragment() {

    internal lateinit var jadwalArrayList: ArrayList<ItemJadwal>
    internal lateinit var jadwalRecyclerView: RecyclerView
    private val TAG= "Disini Fragment 1 "
    var pos: Int?=null

    fun FragmentMenu1(nPos: Int){
        pos=nPos
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_menu_fragment1, container, false)
        Log.d(TAG, "onCreateView")

        jadwalArrayList = ArrayList()

        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Area 81", "23 agustus 14.00 - 15.00", R.drawable.icon_gun))
        jadwalArrayList.add(ItemJadwal("Gloria", "24 agustus 14.00 - 15.00", R.drawable.icon_shuttle_cock))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.drawable.icon_bola))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.drawable.icon_bola))

        jadwalRecyclerView = view.findViewById(R.id.recycle_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        jadwalRecyclerView.layoutManager = linearLayoutManager
        jadwalRecyclerView.isFocusable=false

        val itemAdapter = ItemJadwalAdapter(jadwalArrayList, context)
        jadwalRecyclerView.adapter = itemAdapter

        val btn_F = view.findViewById(R.id.btn_futsal) as Button
        val btn_Bul = view.findViewById(R.id.btn_bultang) as Button
        val btn_A = view.findViewById(R.id.btn_asg) as Button
        val btn_bow = view.findViewById(R.id.btn_bowling) as Button


        btn_F.setOnClickListener {
            Log.d(TAG, "tombol futsal diklik")
            val intent = Intent(this.context, ActivityPesanFutsal::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        btn_Bul.setOnClickListener {
            Log.d(TAG, "tombol bultang diklik")
            val intent = Intent(this.context, ActivityPesanBultang::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        btn_A.setOnClickListener {
            Log.d(TAG, "tombol asg diklik")
            val intent = Intent(this.context, ActivityPesanASG::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        btn_bow.setOnClickListener {
            Log.d(TAG, "tombol bowling diklik")
            val intent = Intent(this.context, ActivityPesanBowling::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        return view
    }


}// Required empty public constructor
