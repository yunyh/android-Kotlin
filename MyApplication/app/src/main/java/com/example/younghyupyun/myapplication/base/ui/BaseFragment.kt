package com.example.younghyupyun.myapplication.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.younghyupyun.myapplication.base.annotations.LifecycleScope
import com.example.younghyupyun.myapplication.base.annotations.RequiredViewModel

abstract class BaseFragment : Fragment() {

    private val viewModel by lazy {
        try {
            checkNotNull(javaClass.getAnnotation(RequiredViewModel::class.java)) {}.run {
                if (scope == LifecycleScope.ACTIVITY) {
                    checkNotNull(activity) { throw IllegalStateException("activity is null") }.let {
                        ViewModelProviders.of(it)
                    }
                } else {
                    ViewModelProviders.of(this@BaseFragment)
                }.get(viewModelClass.java)
            }
        } catch (e: Exception) {

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}