package com.example.recyclerviewcolors.recycler

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolors.R

class MyView(myRow : View) : RecyclerView.ViewHolder(myRow) {
    var name = myRow.findViewById<TextView>(R.id.txtName)
    var code = myRow.findViewById<TextView>(R.id.txtCode)
    var row = myRow.findViewById<LinearLayout>(R.id.row)
}