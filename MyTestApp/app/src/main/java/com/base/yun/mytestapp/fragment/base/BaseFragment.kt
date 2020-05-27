package com.base.yun.mytestapp.fragment.base

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.base.yun.mytestapp.viewmodel.base.MyViewModel

/**
 * Created by YounghyubYun on 2018. 1. 8..
 * ViewModel provider with AndroidViewModel Provider
 * (https://developer.android.com/reference/android/arch/lifecycle/package-summary.html)
 */

abstract class BaseFragment<V : MyViewModel> : androidx.fragment.app.Fragment() {
    val viewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        viewModelProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run {
            viewModel
        }
    }

    private fun viewModelProvider(): V {
        return ViewModelProviders.of(this).get(setViewModel())
    }

    abstract fun setViewModel(): Class<V>
}