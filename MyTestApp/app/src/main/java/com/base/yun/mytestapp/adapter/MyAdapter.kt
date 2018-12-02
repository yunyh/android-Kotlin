package com.base.yun.mytestapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.databinding.ListItemBinding
import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.viewmodel.EventViewModel
import com.bumptech.glide.Glide

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter : ListAdapter<GitHubEventsModel, MyAdapter.MyViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<GitHubEventsModel>() {

            override fun areItemsTheSame(oldItem: GitHubEventsModel, newItem: GitHubEventsModel): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: GitHubEventsModel, newItem: GitHubEventsModel): Boolean = oldItem == newItem //return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder =
            MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(EventViewModel.make(it))
        }
    }

    class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ListItemBinding? = DataBindingUtil.bind(itemView)

        fun bind(eventsViewModel: EventViewModel) = binding?.let {
            it.model = eventsViewModel
            Glide.with(itemView.context).load(eventsViewModel.actorData.get()?.avatarUrl).into(it.imgProfile)
        }

    }
}