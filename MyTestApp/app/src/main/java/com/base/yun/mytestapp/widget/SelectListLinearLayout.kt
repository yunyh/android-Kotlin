package com.base.yun.mytestapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_select_item_view.view.*

class SelectListLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private var selectList = ArrayList<SelectorViewHolder>()


    fun setList() {
        removeAllViews()
    }


    private class ViewHolder(private val view: View) : SelectorViewHolder {
        override fun selected() {
            view.isSelected = true
            view.item_selected_icon.isSelected = true
        }

        override fun unSelected() {
        }
    }
}