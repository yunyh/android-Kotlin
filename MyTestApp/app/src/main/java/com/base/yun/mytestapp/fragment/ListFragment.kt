package com.base.yun.mytestapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.viewmodel.mydata.MyViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : Fragment() {

    private lateinit var callback: ListFragmentCallback

    private val myAdapter by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        MyAdapter(object : MyAdapter.ItemClickCallback {
            override fun onClick(view: View, item: MyModel) {
                callback?.run {
                    onItemClickListener(item)
                }
                /* if (activity is MainActivity) {
                     (activity as MainActivity).showDetailFragment(item)
                 }*/
                Toast.makeText(context, "Data : " + item.data, Toast.LENGTH_LONG).show()
            }
        })
    }

    private val viewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
                .providerList()
                .observe(this@ListFragment, Observer(myAdapter::setList))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        run {
            viewModel
        }
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(list) {
            setHasFixedSize(true)
            this.adapter = myAdapter
        }
        Log.d("ListFragment", "" + list.adapter.itemCount)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            if (it is ListFragmentCallback) {
                callback = it
            }
        }
    }

    interface ListFragmentCallback {
        fun onItemClickListener(item: MyModel)
    }
}