package com.me.instafeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RandAdapter(private val context: Context, private var randomList: ArrayList<DataResponse>) : RecyclerView.Adapter<RandAdapter.RandViewHolder>() {

    inner class RandViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var title: TextView = v.findViewById(R.id.text_rand_item)
        var item: ImageView = v.findViewById(R.id.image_rand_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandViewHolder {
        // create a new view
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.rand_item, parent, false)

        return RandViewHolder(v)
    }

    override fun onBindViewHolder(holder: RandViewHolder, position: Int) {
        holder.title.text = getItem(position).title
        Glide.with(context)
            .load(getItem(position).images.original.url)
            .centerCrop()
            .into(holder.item)
    }

    override fun getItemCount(): Int {
        return randomList.size
    }

    private fun getItem(position: Int): DataResponse {
        return randomList[position]
    }

    fun addGif(rand: DataResponse) {
        randomList.add(rand)
        notifyItemInserted(itemCount - 1)
    }
}
