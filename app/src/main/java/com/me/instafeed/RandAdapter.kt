package com.me.instafeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RandAdapter(
    private val context: Context,
    private var randomList: ArrayList<DataResponse>,
    private val onItemClicked: (DataResponse) -> Unit
) : RecyclerView.Adapter<RandAdapter.RandViewHolder>() {

    inner class RandViewHolder(v: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(v) {
        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        var title: TextView = v.findViewById(R.id.text_rand_item)
        var item: ImageView = v.findViewById(R.id.image_rand_item)
        var share: ImageView = v.findViewById(R.id.btn_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandViewHolder {
        // create a new view
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.rand_item, parent, false)

        return RandViewHolder(v) {
            onItemClicked(randomList[it])
        }
    }

    override fun onBindViewHolder(holder: RandViewHolder, position: Int) {
        holder.title.text = getItem(position).title
        Glide.with(context)
            .load(getItem(position).images.original.url)
            .centerInside()
            .into(holder.item)
        holder.share.setOnClickListener { onItemClicked(getItem(position)) }
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
