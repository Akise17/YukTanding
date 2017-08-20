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

class ItemTimAdapter(private val items: ArrayList<ItemTim>, private val context: Context) : RecyclerView.Adapter<ItemTimAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var namaTim: TextView
        internal var statusTim: TextView
        internal var imgTim: ImageView

        init {

            namaTim = itemView.findViewById(R.id.txt_namaTim) as TextView
            statusTim = itemView.findViewById(R.id.txt_statusTim) as TextView
            imgTim = itemView.findViewById(R.id.img_tim) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tim, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item2 = items[position]
        holder.namaTim.text = item2.namaTim
        holder.statusTim.text = item2.statusTim
        holder.imgTim.setImageResource(item2.imgTim)

        holder.itemView.setOnClickListener { Log.d("Adapter", "Clicked Position: " + position) }

        holder.imgTim.setOnClickListener { Log.d("Adapter", "Profile Clicked " + position) }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
