package com.base.yun.mytestapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.base.yun.mytestapp.MainActivity
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : Fragment() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        activity?.let {
            ViewModelProviders.of(it).get(MyViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = MyAdapter(object : MyAdapter.ItemClickCallback {
            override fun onClick(item: MyModel) {
                if (activity is MainActivity) {
                    (activity as MainActivity).showDetailFragment(item)
                }
                Toast.makeText(context, "Data : " + item.data, Toast.LENGTH_LONG).show()
            }
        })

        //val adapter = MyAdapter(null)
        list.adapter = adapter
        viewModel.let {
            viewModel!!.providerList().observe(this, Observer(adapter::setList))
        }

        Log.d("ListFragment", "" + list.adapter.itemCount)
    }


}