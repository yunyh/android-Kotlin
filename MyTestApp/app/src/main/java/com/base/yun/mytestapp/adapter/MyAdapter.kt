package com.base.yun.mytestapp.adapter

import android.arch.paging.PagedListAdapter
import android.graphics.drawable.Drawable
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.provider.ScheduleEntity
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyAdapter(private var listener: ItemClickCallback) :
        PagedListAdapter<ScheduleEntity, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ScheduleEntity>() {

            override fun areItemsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem.id == newItem.id //return

            override fun areContentsTheSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean = oldItem == newItem //return
        }
        private const val FIRST_POSITION: Int = 0
        const val VIEW_TYPE_ADD = 1000
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = MyViewHolder(parent, R.layout.list_item)

        holder.itemView.setOnClickListener { view ->
            val index = holder.adapterPosition - 1
            when (index) {
                RecyclerView.NO_POSITION -> return@setOnClickListener
                else -> getItem(index)?.let { listener.onClick(view, it) }
            }
        }
        return holder
    }

    private fun switchAddColumn(view: EditText) {
        with(view) {
            val setter = !isEnabled
            isClickable = setter
            isEnabled = setter
            isFocusable = setter
            isFocusableInTouchMode = setter
            if (setter) {
                if (TextUtils.isEmpty(text)) {
                    hint = "input"
                }
                requestFocus()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if (holder is MyViewHolder) {
                holder.bind(it)
            }
        }
    }


    @FunctionalInterface
    interface ItemClickCallback {
        fun onClick(view: View, item: ScheduleEntity)
    }

    @FunctionalInterface
    interface AddItemClickCallback {
        fun onEditClick(view: View)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        /* return when (position) {
            // FIRST_POSITION -> VIEW_TYPE_ADD
             else ->
         }*/
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    open inner class MyViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

        private lateinit var drawableMore: Drawable
        private lateinit var drawableLess: Drawable

        fun bind(item: ScheduleEntity) {
            with(itemView) {
                item_id.text = item.id.toString()
                item_data.text = item.title
                item_date.text = SimpleDateFormat("yyyyMMdd hh:mm:ss", Locale.KOREA).format(item.date)
                item_more_desc.text = item.desc
                drawableMore = ContextCompat.getDrawable(context, R.drawable.ic_expand_more_black_24dp)!!
                drawableLess = ContextCompat.getDrawable(context, R.drawable.ic_expand_less_black_24dp)!!
                switchExpandMoreButton()
            }
        }

        private fun switchExpandMoreButton() {
            itemView.item_expand_button.setOnClickListener {
                with(itemView.item_more_desc) {
                    visibility = when (visibility) {
                        View.VISIBLE -> {
                            itemView.item_expand_button.setImageDrawable(drawableMore)
                            clearFocus()
                            View.GONE
                        }
                        else -> {
                            itemView.item_expand_button.setImageDrawable(drawableLess)
                            requestFocus()
                            View.VISIBLE
                        }
                    }
                }
            }
        }

    }

    inner class MyEditViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
        fun bind() {
        }
    }
}