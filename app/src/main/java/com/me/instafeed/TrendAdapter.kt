package com.me.instafeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TrendAdapter(private val context: Context, private var trendingList: ArrayList<DataResponse>) : RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {

    inner class TrendViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var title: TextView = v.findViewById(R.id.text_trend_item)
        var item: ImageView = v.findViewById(R.id.image_trend_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        // create a new view
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.trend_item, parent, false)

        return TrendViewHolder(v)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        Glide.with(context)
            .load(getItem(position).images.original.url)
            .circleCrop()
            .into(holder.item)
        holder.title.text = getItem(position).username

    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    private fun getItem(position: Int): DataResponse {
        return trendingList[position]
    }

    fun addGif(trend: ArrayList<DataResponse>) {
        trendingList.addAll(trend)
        notifyItemInserted(itemCount - 1)
    }
}
