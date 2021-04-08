package com.chaos.takemap.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaos.takemap.R

class ElementAdapter: RecyclerView.Adapter<ElementAdapter.ViewHolder> {

    var context:Context? = null

    constructor(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_element, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
}