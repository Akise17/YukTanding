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
            name = itemView.findViewById<TextView>(R.id.txt_namaLapangan)
            waktu = itemView.findViewById<TextView>(R.id.txt_jamMain)
            imgLapangan = itemView.findViewById<ImageView>(R.id.img_profil)
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

        holder.itemView.setOnClickListener { Log.d("Disini AdapJadwal", "Clicked Position: " + position) }

        holder.imgLapangan.setOnClickListener { Log.d("Disini AdapJadwal", "Profile Clicked " + position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
