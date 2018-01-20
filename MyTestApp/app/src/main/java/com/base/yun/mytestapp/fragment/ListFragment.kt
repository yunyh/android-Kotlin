package com.base.yun.mytestapp.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.fragment.base.BaseFragment
import com.base.yun.mytestapp.provider.ScheduleEntity
import com.base.yun.mytestapp.viewmodel.base.MyViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : BaseFragment<MyViewModel>() {

    override fun setViewModel(): Class<MyViewModel> {
        return MyViewModel::class.java
    }

    private lateinit var callback: ListFragmentCallback

    private val myAdapter by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        MyAdapter(object : MyAdapter.ItemClickCallback {
            override fun onClick(view: View, item: ScheduleEntity) {
                with(callback) {
                    onItemClickListener(item)
                }
                Toast.makeText(context, "Data : " + item.desc, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
//        viewModel.providerList().observe(this@ListFragment, Observer(myAdapter::setList))
        viewModel.providerSchedule().observe(this@ListFragment, Observer(myAdapter::setList))
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list?.let {
          //  it.addItemDecoration(object : DividerItemDecoration(context, VERTICAL) {})
            it.setHasFixedSize(true)
            it.adapter = myAdapter
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            if (it is ListFragmentCallback) {
                callback = it
            }
        }
    }

    override fun onDetach() {
        callback.let {
            null
        }
        super.onDetach()
    }

    interface ListFragmentCallback {
        fun onItemClickListener(item: ScheduleEntity)
    }
}