package id.yuktanding.yuktanding


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

class FragmentMenu3 : Fragment() {

    internal lateinit var timArrayList: ArrayList<ItemTim>
    internal lateinit var timRecyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_menu_fragment3, container, false)

        timArrayList = ArrayList()

        timArrayList.add(ItemTim("Lontong FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Petis FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Akise FC", "P:33 W:20 D:5 L:8", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Lontong FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Petis FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Akise FC", "P:33 W:20 D:5 L:8", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Lontong FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Petis FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Akise FC", "P:33 W:20 D:5 L:8", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Lontong FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Petis FC", "P:33 W:11 D:2 L:20", R.mipmap.yuklogo))
        timArrayList.add(ItemTim("Akise FC", "P:33 W:20 D:5 L:8", R.mipmap.yuklogo))

        timRecyclerView = view.findViewById(R.id.tim_recyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        timRecyclerView.layoutManager = linearLayoutManager

        val itemAdapter = ItemTimAdapter(timArrayList, context)
        timRecyclerView.adapter = itemAdapter

        return view
    }

}// Required empty public constructor
