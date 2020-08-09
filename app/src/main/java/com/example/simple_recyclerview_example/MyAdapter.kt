package com.example.simple_recyclerview_example


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(var items: Array<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
            = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mytext.text = items [position]
     }

    inner class MyViewHolder (textRwView: View) : RecyclerView.ViewHolder(textRwView) {
        var mytext = textRwView.findViewById<TextView>(R.id.recyclerView_textView)
    }


}