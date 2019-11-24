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

    private val presetList = ArrayList<SelectViewHolder>()
    private var manualViewHolder: SelectViewHolder? = null
    private val selectedViewHolder
        get() = presetList.getOrNull(selectIndex) ?: manualViewHolder

    private var selectIndex = 0
    private val manualSelectIndex
        get() = presetList.lastIndex + 1

    private lateinit var itemClick: () -> Unit
    private lateinit var amountClick: (amount: Double) -> Unit

    init {
        orientation = VERTICAL
        if (isInEditMode) {
            addView(addPriceButtonView)
        }
    }

    fun setList(preset: List<PriceSelectorItemViewModel.ViewModel>) {
        removeAllViews()
        preset.mapIndexedNotNullTo(presetList) { index, model ->
            childBinding?.let { binding ->
                SelectViewHolder.viewModel(binding, model).apply {
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

    fun addItem(model: PriceSelectorItemViewModel) {
        presetList.find { it.amount == model.amount }?.let {
            internalItemClick(presetList.indexOf(it))
        } ?: addManualPrice(model)
    }

    private fun addManualPrice(model: PriceSelectorItemViewModel) {
        manualViewHolder?.run {
            removeView(root)
        }
        SelectViewHolder.viewModel(childBinding ?: return, model).apply {
            root.setOnClickListener {
                internalItemClick(manualSelectIndex)
            }
        }.also {
            manualViewHolder = it
            internalItemClick(manualSelectIndex)
            addView(it.root, manualSelectIndex)
        }
        invalidate()
    }

    private fun internalItemClick(index: Int) {
        if (index == -1) {
            return
        }

        presetList.getOrNull(selectIndex)?.unSelected() ?: manualViewHolder?.unSelected()
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
            fun viewModel(binding: LayoutSelectItemViewBinding, model: PriceSelectorItemViewModel) =
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