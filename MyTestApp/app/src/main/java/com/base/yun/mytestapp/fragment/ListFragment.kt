package com.base.yun.mytestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : Fragment() {

    private val myAdapter: MyAdapter by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        MyAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo_list.adapter = myAdapter
        activity?.let {
            ViewModelProviders.of(it).get(MainActivityViewModel::class.java).apply {
                eventsLiveData.observe(this@ListFragment, Observer(myAdapter::submitList))
            }.getReceivedEvents("yunyh")
        }
    }
}