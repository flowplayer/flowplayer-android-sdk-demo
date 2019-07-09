package com.flowplayer.player.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(private val items: ArrayList<Item>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    interface OnItemClickListener {
        fun onItemClicked(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.titleView.text = items[position].title
        holder.subtitleView.text = items[position].subtitle

        holder.itemView.setOnClickListener {
            listener.onItemClicked(items[position])
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.title)
        val subtitleView: TextView = itemView.findViewById(R.id.subtitle)
    }
}