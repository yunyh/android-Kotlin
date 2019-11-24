package com.base.yun.mytestapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.databinding.LayoutSelectItemViewBinding
import kotlin.math.max

class PriceSelectListLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private var presetList = ArrayList<SelectViewHolder>()
    private var manualViewholder: SelectViewHolder? = null
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


    private var selectIndex = 0
    private val manualSelectIndex
        get() = presetList.lastIndex + 1
    private val lastChildIndex
        get() = max(0, childCount - 1)

    private lateinit var itemClick: () -> Unit
    private lateinit var amountClick: (amount: Double) -> Unit

    init {
        orientation = VERTICAL
        if (isInEditMode) {
            addView(addPriceButtonView)
        }
    }

    fun setList() {
        removeAllViews()
        arrayListOf(PriceSelectorItemViewModel.ViewModel("1st installment", 100.0),
                PriceSelectorItemViewModel.ViewModel("All received", 500.0),
                PriceSelectorItemViewModel.ViewModel("Not Received", 0.0))
                .mapIndexedNotNullTo(presetList) { index, model ->
                    //  presetList.addAll(it)
                    childBinding?.let { binding ->
                        SelectViewHolder.viewModel(binding, model).apply {
                            addView(root)
                            root.setOnClickListener {
                                internalItemClick(index, model)
                            }
                        }
                    }
                }
        internalItemClick(selectIndex, presetList[selectIndex])
        addView(addPriceButtonView)
    }

    fun addItem(model: PriceSelectorItemViewModel) {
        presetList.find { it.amount == model.amount }?.let {
            internalItemClick(presetList.indexOf(it), model)
            /* presetList.getOrNull(selectIndex)?.unSelected() ?: manualViewholder?.unSelected()
             selectIndex = presetList.indexOf(it)
             it.selected()*/
        } ?: addManualPrice(model)
    }

    private fun addManualPrice(model: PriceSelectorItemViewModel) {
        // presetList.getOrNull(selectIndex)?.unSelected()
        manualViewholder?.run {
            removeView(root)
        }

        addView(SelectViewHolder.viewModel(childBinding ?: return, model).apply {
            selected()
            root.setOnClickListener {
                internalItemClick(manualSelectIndex, model)
            }
        }.also {
            manualViewholder = it
            internalItemClick(manualSelectIndex, model)
        }.root, lastChildIndex)
        invalidate()
    }

    private fun internalItemClick(index: Int, model: PriceSelectorItemViewModel) {
        if (index == -1) {
            return
        }

        presetList.getOrNull(selectIndex)?.unSelected() ?: manualViewholder?.unSelected()
        selectIndex = index
        presetList.getOrNull(selectIndex)?.selected() ?: manualViewholder?.selected()
        if (::itemClick.isInitialized) {
            amountClick(model.amount)
        }
    }

    fun setOnAddAmountClickListener(callback: () -> Unit) {
        itemClick = callback
    }

    fun setOnItemClickListener(callback: (amount: Double) -> Unit) {
        amountClick = callback
        //  amountClick(presetList.getOrNull(selectIndex)?.amount ?: manualViewholder?.amount ?: return)
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