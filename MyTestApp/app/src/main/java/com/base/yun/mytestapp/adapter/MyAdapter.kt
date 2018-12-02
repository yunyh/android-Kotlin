package com.base.yun.mytestapp.adapter

import androidx.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.databinding.ListItemBinding
import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.viewmodel.EventViewModel
import com.bumptech.glide.Glide

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter : ListAdapter<GitHubEventsModel, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<GitHubEventsModel>() {

            override fun areItemsTheSame(oldItem: GitHubEventsModel, newItem: GitHubEventsModel): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: GitHubEventsModel, newItem: GitHubEventsModel): Boolean = oldItem == newItem //return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if (holder is MyViewHolder) {
                holder.bind(EventViewModel.Provider.make(it))
            }
        }
    }

    open inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ListItemBinding? = DataBindingUtil.bind(itemView)

        fun bind(eventsViewModel: EventViewModel) = binding?.let {
            it.model = eventsViewModel
            Glide.with(itemView.context).load(eventsViewModel.actorData.get()?.avatarUrl).into(it.imgProfile)
        }

    }
}