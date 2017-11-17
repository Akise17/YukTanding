package id.yuktanding.yuktanding

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by ekkyh on 11/7/2017.
 */

class ItemMatchAdapter (private val items: ArrayList<ItemMatch>, private val context: Context) : RecyclerView.Adapter<ItemMatchAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var merah_nama: TextView = itemView.findViewById(R.id.tim_merah_name)
        internal var merah_desc: TextView = itemView.findViewById(R.id.tim_merah_desc)
        internal var merah_img: ImageView = itemView.findViewById(R.id.tim_merah_img)
        internal var biru_nama: TextView = itemView.findViewById(R.id.tim_biru_name)
        internal var biru_desc: TextView = itemView.findViewById(R.id.tim_biru_desc)
        internal var biru_img: ImageView = itemView.findViewById(R.id.tim_biru_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_match,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val item2 = items[position]
        //=======================Merah========================
        holder!!.merah_nama.text = item2.tim_merah_nama
        holder!!.merah_desc.text = item2.tim_merah_desc
        Picasso.with(context)
                .load(item2.tim_merah_img)
                .into(holder.merah_img)

        //=======================Biru========================
        holder!!.biru_nama.text = item2.tim_biru_nama
        holder!!.biru_desc.text = item2.tim_biru_desc
        Picasso.with(context)
                .load(item2.tim_biru_img)
                .error(R.drawable.icon_bola_small)
                .into(holder.biru_img)

        holder.itemView.setOnClickListener { Log.d("Disini MatchAdapter", "Clicked Position: " + position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}