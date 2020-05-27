package com.example.younghyupyun.radiobuttonswiching.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.younghyupyun.radiobuttonswiching.R
import com.example.younghyupyun.radiobuttonswiching.databinding.ItemPlantBinding
import com.example.younghyupyun.radiobuttonswiching.models.PlantsModel


class PlantsListAdapter : ListAdapter<PlantsModel, PlantsListAdapter.PlantsViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PlantsModel>() {
            override fun areItemsTheSame(oldItem: PlantsModel, newItem: PlantsModel): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: PlantsModel, newItem: PlantsModel): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsViewHolder =
        PlantsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false))

    override fun onBindViewHolder(holder: PlantsViewHolder, position: Int) {
        getItem(position).let {
            holder.binding?.model = it
        }
    }


    class PlantsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ItemPlantBinding>(itemView)
    }
}