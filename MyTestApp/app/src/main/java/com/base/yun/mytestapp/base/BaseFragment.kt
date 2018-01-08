package com.base.yun.mytestapp.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.viewmodel.mydata.MyViewModel

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */

abstract class BaseFragment<V : MyViewModel> : Fragment() {
    val viewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        viewModelProvider()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        run {
            viewModel
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun viewModelProvider(): V {
        return ViewModelProviders.of(this).get(setViewModel())
    }

    abstract fun setViewModel(): Class<V>
}