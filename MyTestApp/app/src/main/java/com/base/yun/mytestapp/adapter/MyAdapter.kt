package com.base.yun.mytestapp.adapter

import android.arch.paging.PagedListAdapter
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.model.MyModel

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter(private val listener: ItemClickCallback?) : PagedListAdapter<MyModel, MyAdapter.MyViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffCallback<MyModel>() {

            override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel): Boolean = oldItem == newItem //return
        }
    }

    interface ItemClickCallback {
        fun onClick(item: MyModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        getItem(position)?.let { holder?.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val viewHolder = MyViewHolder(parent, R.layout.list_item)
        listener?.let {
            viewHolder.itemView.setOnClickListener { getItem(viewHolder.adapterPosition)?.let { listener.onClick(it) } }
        }
        return viewHolder
    }

    inner class MyViewHolder(parent: ViewGroup?, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(layoutId, parent, false)) {
        fun bind(item: MyModel) {
            itemView.findViewById<TextView>(R.id.item_id).text = item.id.toString()
            itemView.findViewById<TextView>(R.id.item_data).text = item.data
        }
    }
}