package com.base.yun.mytestapp.widget

import android.view.View

interface PriceSelectorItemViewModel {

    val desc: String

    val amount: Double

    val amountText: String
        get() = amount.toString()

    val visibleAmountText
        get() = if (amount <= 0) View.GONE else View.VISIBLE

    fun selected()

    fun unSelected()

    class ViewModel(override val desc: String,
                    override val amount: Double) : PriceSelectorItemViewModel {
        override fun selected() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun unSelected() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}