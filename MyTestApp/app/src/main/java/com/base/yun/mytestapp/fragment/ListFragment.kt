package com.base.yun.mytestapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : Fragment() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val adapter = MyAdapter()
        list.adapter = adapter
        viewModel.dump.observe(this, Observer(adapter::setList))

        Log.d("ListFragment", "" + list.adapter.itemCount )
    }
}