package com.example.simple_recyclerview_example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(var items: Array<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
            = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val number = items [position]
//        holder.mytext.text = items [position]
        tracker?.let {
            holder.bind(position, it.isSelected(position.toLong()))
        }
     }


    override fun getItemId(position: Int): Long = position.toLong()

    inner class MyViewHolder (textRwView: View) : RecyclerView.ViewHolder(textRwView) {
        var mytext = textRwView.findViewById<TextView>(R.id.recyclerView_textView)

        fun bind(value: Int, isActivated: Boolean = false) {
            mytext.text = items [value]
            itemView.isActivated = isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }



}