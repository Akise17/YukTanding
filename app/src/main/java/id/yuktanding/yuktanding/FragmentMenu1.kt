package id.yuktanding.yuktanding


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class FragmentMenu1 : Fragment() {

    internal lateinit var jadwalArrayList: ArrayList<ItemJadwal>
    internal lateinit var jadwalRecyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_menu_fragment1, container, false)

        jadwalArrayList = ArrayList()

        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "22 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "23 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))
        jadwalArrayList.add(ItemJadwal("Liverpool Futsal", "24 agustus 14.00 - 15.00", R.mipmap.ic_launcher_round))

        jadwalRecyclerView = view.findViewById(R.id.recycle_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        jadwalRecyclerView.layoutManager = linearLayoutManager

        val itemAdapter = ItemJadwalAdapter(jadwalArrayList, context)
        jadwalRecyclerView.adapter = itemAdapter

        return view
    }

}// Required empty public constructor
