package com.chaos.takemap.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaos.takemap.R
import com.chaos.takemap.model.Content
import com.chaos.takemap.model.SettingData
import com.chaos.takemap.model.SettingDataItem
import kotlinx.android.synthetic.main.layout_item_element.view.*
import kotlinx.android.synthetic.main.layout_item_setting.view.*

class SettingAdapter : RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    var context: Context? = null
    var settingData: SettingData? = null

    constructor(context: Context, settingData: SettingData) {
        this.context = context
        this.settingData = settingData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_setting, parent, false)
        return ViewHolder(itemView)
    }

    private var showChildItem = false
    private var childItemType = 0
    private var childItemRecycleView: RecyclerView? = null
    private var ivArrow: ImageView? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (settingData != null) {
            val settingDataItem = settingData!![position]
            val name = settingDataItem.name
            holder.itemView.tvRefSetting.text = name.substring(0, 1)
            holder.itemView.tvNameSetting.text = name

            addChildItems(settingDataItem, holder.itemView.rvSettingItem)


            holder.itemView.setOnClickListener {
                if (!showChildItem) {
                    showChildItem(settingDataItem, holder)
                    return@setOnClickListener
                }

                if (childItemType != settingDataItem.type) {
                    //先隐藏之前的子条目
                    hideChildItems()
                    //再显示当前的子条目
                    showChildItem(settingDataItem, holder)
                } else {
                    hideChildItems()
                    showChildItem = false
                }
            }

        }
    }

    private fun showChildItem(
        settingDataItem: SettingDataItem,
        holder: ViewHolder
    ) {
        showChildItem = true
        childItemType = settingDataItem.type
        childItemRecycleView = holder.itemView.rvSettingItem
        ivArrow = holder.itemView.ivSettingArrow
        ivArrow!!.rotation = 90f
        holder.itemView.rvSettingItem.visibility = View.VISIBLE
    }

    private fun hideChildItems() {
        if (childItemRecycleView != null) {
            childItemRecycleView!!.visibility = View.GONE
            if (ivArrow != null) {
                ivArrow!!.rotation = 0f
            }
            notifyDataSetChanged()
        }
    }

    private fun addChildItems(settingDataItem: SettingDataItem, rvSettingItem: RecyclerView) {
        if (settingDataItem.content != null && settingDataItem.content.isNotEmpty()) {
            rvSettingItem.layoutManager = LinearLayoutManager(this.context)
            val childItemAdapter =
                ChildSettingAdapter(this.context!!, settingDataItem.content as MutableList<Content>)
            rvSettingItem.adapter = childItemAdapter
            childItemAdapter.setOnItemClickListener { position, content ->
                if (mapSelectListener != null){
                    mapSelectListener!!.invoke(settingDataItem.type,content)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return settingData?.size ?: 0
    }

    private var mapSelectListener:((Int,Content)->Unit)? = null

    fun setOnMapDataSelectListener(mapSelectListener:(Int,Content)->Unit){
        this.mapSelectListener = mapSelectListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}