package com.base.yun.mytestapp.adapter

import android.arch.paging.PagedListAdapter
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.provider.ScheduleEntity
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter(private var listener: ItemClickCallback) :
        PagedListAdapter<ScheduleEntity, MyAdapter.MyViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffCallback<ScheduleEntity>() {

            override fun areItemsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem == newItem //return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = MyViewHolder(parent, R.layout.list_item)
        viewHolder.itemView.setOnClickListener { view ->
            if (viewHolder.adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }
            getItem(viewHolder.adapterPosition)?.let { listener.onClick(view, it) }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    @FunctionalInterface
    interface ItemClickCallback {
        fun onClick(view: View, item: ScheduleEntity)
    }

    inner class MyViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
        fun bind(item: ScheduleEntity) {
            itemView.item_id.text = item.id.toString()
            itemView.item_data.text = item.desc
            itemView.item_date.text = SimpleDateFormat("yyyyMMdd hh:mm:ss", Locale.KOREA).format(item.date)
        }
    }
}