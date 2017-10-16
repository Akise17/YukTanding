package id.yuktanding.yuktanding

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class ItemVenueAdapter(private val items: ArrayList<ItemVenue>, private val context: Context) : RecyclerView.Adapter<ItemVenueAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var nama: TextView
        internal var desc: TextView
        internal var img: ImageView

        init {
            nama= itemView.findViewById(R.id.venue_name) as TextView
            desc= itemView.findViewById(R.id.venue_desc) as TextView
            img = itemView.findViewById(R.id.venue_img) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_venue, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val item2 = items[position]
        holder!!.nama.text = item2.namaVenue
        holder.desc.text = item2.descVenue
        holder.img.setImageResource(item2.imgVenue)

        holder.itemView.setOnClickListener { Log.d("Disini AdapVenue", "Clicked Position: " + position) }

        holder.img.setOnClickListener { Log.d("Disini AdapVenue", "Profile Clicked " + position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}