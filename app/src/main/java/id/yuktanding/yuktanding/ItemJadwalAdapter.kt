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

class ItemJadwalAdapter(private val items: ArrayList<ItemJadwal>, private val context: Context) : RecyclerView.Adapter<ItemJadwalAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView
        internal var waktu: TextView
        internal var imgLapangan: ImageView

        init {

            name = itemView.findViewById(R.id.txt_namaLapangan) as TextView
            waktu = itemView.findViewById(R.id.txt_jamMain) as TextView
            imgLapangan = itemView.findViewById(R.id.img_profil) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemJadwalAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemJadwalAdapter.MyViewHolder, position: Int) {
        val item2 = items[position]
        holder.name.text = item2.namaLapangan
        holder.waktu.text = item2.jadwalLapangan
        holder.imgLapangan.setImageResource(item2.imgLapangan)

        holder.itemView.setOnClickListener { Log.d("Adapter", "Clicked Position: " + position) }

        holder.imgLapangan.setOnClickListener { Log.d("Adapter", "Profile Clicked " + position) }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
