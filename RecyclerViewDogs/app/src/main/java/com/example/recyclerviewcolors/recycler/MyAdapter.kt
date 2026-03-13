package com.example.recyclerviewcolors.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolors.R
import com.example.recyclerviewcolors.model.DogsResponse
import com.bumptech.glide.Glide

class MyAdapter(var myData : DogsResponse) : RecyclerView.Adapter<MyView>() {

    var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {
        return MyView(LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false))
    }

    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {
        val url = myData.message!![position]
        Glide.with(holder.itemView.context).load(url).into(holder.img)


        holder.row.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = position
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int {
        return myData.message!!.size
    }
}