package com.base.yun.mytestapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.databinding.LayoutSelectItemViewBinding

class PriceSelectListLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val addPriceButtonView by lazy {
        LayoutInflater.from(context).inflate(R.layout.layout_select_item_add_button, this, false).apply {
            setOnClickListener {
                if (::itemClick.isInitialized) {
                    itemClick()
                }
            }
        }
    }
    private val childBinding
        get() = context?.let {
            LayoutSelectItemViewBinding.inflate(LayoutInflater.from(it), this, false)
        }

    private val selectorList = ArrayList<SelectViewHolder>()
    // private var manualViewHolder: SelectViewHolder? = null
    private val selectedViewHolder
        get() = selectorList.getOrNull(selectIndex)

    private var selectIndex = 0
    private var manualIndex = 0

    private lateinit var itemClick: () -> Unit
    private lateinit var amountClick: (amount: Double) -> Unit

    init {
        orientation = VERTICAL
        if (isInEditMode) {
            addView(addPriceButtonView)
        }
    }

    fun setPresetItems(preset: List<PriceSelectorItemViewModel.ViewModel>) {
        removeAllViews()
        manualIndex = preset.size
        preset.mapIndexedNotNullTo(selectorList) { index, model ->
            childBinding?.let { binding ->
                SelectViewHolder.bind(binding, model).apply {
                    root.setOnClickListener {
                        internalItemClick(index)
                    }
                }.also {
                    addView(it.root)
                }
            }
        }
        internalItemClick(selectIndex)
        addView(addPriceButtonView)
    }

    fun addManualItem(model: PriceSelectorItemViewModel) {
        selectorList.find { it.amount == model.amount }?.let {
            internalItemClick(selectorList.indexOf(it))
        } ?: addManualPrice(model)
    }

    private fun addManualPrice(model: PriceSelectorItemViewModel) {
        selectorList.getOrNull(manualIndex)?.run {
            removeView(root)
            selectorList.remove(this)
        }
        SelectViewHolder.bind(childBinding ?: return, model).apply {
            root.setOnClickListener {
                internalItemClick(manualIndex)
            }
        }.also {
            selectorList.add(it)
            // manualViewHolder = it
            internalItemClick(manualIndex)
            addView(it.root, manualIndex)
        }
        invalidate()
    }

    private fun internalItemClick(index: Int) {
        if (index == -1) {
            return
        }

        selectorList.getOrNull(selectIndex)?.unSelected() /*?: manualViewHolder?.unSelected()*/
        selectIndex = index

        selectedViewHolder?.run {
            selected()
            if (::amountClick.isInitialized) {
                amountClick(amount)
            }
        }
    }

    fun setOnAddAmountClickListener(callback: () -> Unit) {
        itemClick = callback
    }

    fun setOnItemClickListener(callback: (amount: Double) -> Unit) {
        amountClick = callback
        internalItemClick(selectIndex)
    }

    private class SelectViewHolder private constructor(private val binding: LayoutSelectItemViewBinding,
                                                       model: PriceSelectorItemViewModel) : PriceSelectorItemViewModel by model {

        companion object {
            @JvmStatic
            fun bind(binding: LayoutSelectItemViewBinding, model: PriceSelectorItemViewModel) =
                    SelectViewHolder(binding, model).apply {
                        binding.model = this
                    }
        }

        val root
            get() = binding.root

        override fun selected() {
            binding.itemSelectedIcon.isSelected = true
        }

        override fun unSelected() {
            binding.itemSelectedIcon.isSelected = false
        }
    }
}