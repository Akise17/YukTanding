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

class ItemVenueAdapter(private val items: ArrayList<ItemVenue>, private val context: Context) : RecyclerView.Adapter<ItemVenueAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var nama: TextView = itemView.findViewById<TextView>(R.id.venue_name)
        internal var desc: TextView = itemView.findViewById<TextView>(R.id.venue_desc)
        internal var img: ImageView = itemView.findViewById<ImageView>(R.id.venue_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_venue, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val item2 = items[position]
        holder!!.nama.text = item2.namaVenue
        holder.desc.text = item2.descVenue
        Picasso.with(context)
                .load(item2.imgVenue)
                .into(holder.img)

        holder.itemView.setOnClickListener { Log.d("Disini VenueAdapter", "Clicked Position: " + position) }

        holder.img.setOnClickListener { Log.d("Disini VenueAdapter", "Profile Clicked " + position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}