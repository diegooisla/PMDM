package com.example.recyclerviewcolors.recycler

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolors.R

class MyView(myRow : View) : RecyclerView.ViewHolder(myRow) {
    var img = myRow.findViewById<ImageView>(R.id.imageView)
    var row = myRow.findViewById<LinearLayout>(R.id.row)
}