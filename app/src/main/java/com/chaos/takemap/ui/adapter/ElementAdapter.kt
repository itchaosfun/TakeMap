package com.chaos.takemap.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaos.takemap.R
import com.chaos.takemap.config.ThemeConfig
import com.chaos.takemap.model.ElementData
import com.chaos.takemap.model.THEME_DARK
import com.chaos.takemap.model.enummodel.RoadElementEnum
import kotlinx.android.synthetic.main.layout_item_element.view.*

class ElementAdapter : RecyclerView.Adapter<ElementAdapter.ViewHolder> {

    var context: Context? = null
    var elementData: ElementData? = null

    constructor(context: Context, elementData: ElementData) {
        this.context = context
        this.elementData = elementData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_element, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (elementData != null) {
            val elementDataItem = elementData!![position]
            holder.itemView.ivItem.setImageResource(RoadElementEnum.valueOfByType(elementDataItem.type))
            holder.itemView.ivItem.setBackgroundColor(
                if (ThemeConfig.theme == THEME_DARK) {
                    R.color.grey
                } else {
                    R.color.grey
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return elementData?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}