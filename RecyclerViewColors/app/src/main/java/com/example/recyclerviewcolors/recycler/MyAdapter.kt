package com.example.recyclerviewcolors.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolors.R
import com.example.recyclerviewcolors.model.Datos
import androidx.core.graphics.toColorInt

class MyAdapter(var myData : Datos) : RecyclerView.Adapter<MyView>() {

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
        //Me cantaba Android para ponerlo de esta forma
        holder.name.text = myData.colors[position].name
        holder.code.text = myData.colors[position].code
        //Usando parseColor también te canta para dejarlo de esta forma.
        holder.row.setBackgroundColor(myData.colors[position].code.toColorInt())

        if(position == selectedPosition) {
            holder.name.setTextColor(myData.colors[position].code.toColorInt())
            holder.code.setTextColor(myData.colors[position].code.toColorInt())
            holder.row.setBackgroundColor(Color.WHITE)
        }else{
            holder.name.setTextColor(Color.WHITE)
            holder.code.setTextColor(Color.WHITE)
            holder.row.setBackgroundColor(myData.colors[position].code.toColorInt())
        }

        holder.row.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = position
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int {
        return myData.colors.size
    }
}