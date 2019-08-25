package com.base.yun.mytestapp.utils

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

fun <T : ViewDataBinding> Activity.bindingContentView(@LayoutRes layoutResId: Int): T = DataBindingUtil.setContentView(this, layoutResId)

fun <T : ViewDataBinding> FragmentActivity.bindingView(@LayoutRes layoutResId: Int): Lazy<T> {
    val initializer: () -> T = { bindingContentView(layoutResId) }
    return SynchronizedViewModelLazy(initializer)
}

