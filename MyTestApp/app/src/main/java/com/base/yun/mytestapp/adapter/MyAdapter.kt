package com.base.yun.mytestapp.adapter

import android.arch.paging.PagedListAdapter
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.provider.ScheduleEntity
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item_input.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter(private var listener: ItemClickCallback) :
        PagedListAdapter<ScheduleEntity, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffCallback<ScheduleEntity>() {

            override fun areItemsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem == newItem //return
        }
        private const val FIRST_POSITION: Int = 0
        const val VIEW_TYPE_ADD = 1000;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder = when (viewType) {
            VIEW_TYPE_ADD -> MyEditViewHolder(parent, R.layout.list_item_input)
            else -> MyViewHolder(parent, R.layout.list_item)
        }
        viewHolder.itemView.setOnClickListener { view ->
            if (viewHolder.adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }
            getItem(viewHolder.adapterPosition)?.let { listener.onClick(view, it) }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_ADD -> {
                if (holder is MyEditViewHolder) {
                    holder.bind()
                }
            }
            else -> getItem(position)?.let {
                if (holder is MyViewHolder) {
                    holder.bind(it)
                }
            }
        }
    }

    @FunctionalInterface
    interface ItemClickCallback {
        fun onClick(view: View, item: ScheduleEntity)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            FIRST_POSITION -> VIEW_TYPE_ADD
            else -> super.getItemViewType(position)
        }
    }

    open inner class MyViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
        fun bind(item: ScheduleEntity) {
            with(itemView) {
                item_id.text = item.id.toString()
                item_data.text = item.desc
                item_date.text = SimpleDateFormat("yyyyMMdd hh:mm:ss", Locale.KOREA).format(item.date)
            }
        }
    }

    inner class MyEditViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
        fun bind() {
            with(itemView) {
                itemView.setOnClickListener {
                    item_add_desc.isEnabled = true
                    notifyItemChanged(FIRST_POSITION)
                    return@setOnClickListener
                }
            }
        }
    }
}