package com.chaos.takemap.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaos.takemap.R
import com.chaos.takemap.model.Content
import kotlinx.android.synthetic.main.layout_item_child_setting.view.*

class ChildSettingAdapter : RecyclerView.Adapter<ChildSettingAdapter.ViewHolder> {

    var context: Context? = null
    var contents: MutableList<Content>? = null

    constructor(context: Context, contents:MutableList<Content>) {
        this.context = context
        this.contents = contents
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_child_setting, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (contents != null) {
            val content = contents!![position]
            val name = content.desc
            holder.itemView.tvChildRefSetting.text = name.substring(0, 1)
            holder.itemView.tvChildNameSetting.text = name
            holder.itemView.setOnClickListener {
                if (onItemClickListener != null){
                    onItemClickListener!!.invoke(position,content)
                }
            }
        }
    }
    private var onItemClickListener:((Int,Content)->Unit)? = null
    fun setOnItemClickListener(onItemClickListener:(Int,Content)->Unit){
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return contents?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}