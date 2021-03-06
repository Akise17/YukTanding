package id.yuktanding.yuktanding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.daimajia.slider.library.SliderLayout

import java.util.ArrayList
import com.daimajia.slider.library.SliderTypes.TextSliderView

class FragmentMenu1 : Fragment() {

    private lateinit var jadwalArrayList: ArrayList<ItemJadwal>
    private lateinit var jadwalRecyclerView: RecyclerView

    private val TAG = "Disini Fragment 1 "
    var i = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_menu_fragment1, container, false)

        val sliderShow = view.findViewById<SliderLayout>(R.id.img_event)
        val textSliderView = TextSliderView(context)
        val textSliderView2 = TextSliderView(context)

        textSliderView
                .description("Promo 1")
                .image(R.drawable.event1)
        textSliderView2
                .description("Promo 2")
                .image(R.drawable.event2)

        sliderShow.addSlider(textSliderView)
        sliderShow.addSlider(textSliderView2)

        //==========================================================================================
        jadwalArrayList = ArrayList()

        jadwalArrayList.add(ItemJadwal("Fun Futsal SMAN 112", "BYWI Futsal | 18 Des 14:00", R.drawable.icon_bola_small))
        jadwalArrayList.add(ItemJadwal("Safe the hostage", "Area 81 | 20 Des 19:00", R.drawable.icon_gun_small))
        jadwalArrayList.add(ItemJadwal("Turnamen Kopi Tubruk", "Futsal Center 27 | 24Des 08:00", R.drawable.icon_bola_small))

        jadwalRecyclerView = view.findViewById<RecyclerView>(R.id.recycle_view)
        val linearLayoutManager = LinearLayoutManager(context)
        jadwalRecyclerView.layoutManager = linearLayoutManager
        jadwalRecyclerView.isFocusable = false

        val itemAdapter = ItemJadwalAdapter(jadwalArrayList, context)
        jadwalRecyclerView.adapter = itemAdapter

        val btn_F = view.findViewById<Button>(R.id.btn_futsal)
        val btn_Bul = view.findViewById<Button>(R.id.btn_bultang)
        val btn_A = view.findViewById<Button>(R.id.btn_asg)
        val btn_bow = view.findViewById<Button>(R.id.btn_bowling)


        btn_F.setOnClickListener {
            Log.d(TAG, "tombol futsal diklik")
            val intent = Intent(this.context, ActivityFindMatch::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        /*btn_Bul.setOnClickListener {
            Log.d(TAG, "tombol bultang diklik")
            val intent = Intent(this.context, ActivityPesanBultang::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }*/
        /*btn_A.setOnClickListener {
            Log.d(TAG, "tombol asg diklik")
            val intent = Intent(this.context, ActivityPesanASG::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }*/
        /*btn_bow.setOnClickListener {
            Log.d(TAG, "tombol bowling diklik")
            val intent = Intent(this.context, ActivityPesanBowling::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }*/

        return view
    }

}// Required empty public constructor
