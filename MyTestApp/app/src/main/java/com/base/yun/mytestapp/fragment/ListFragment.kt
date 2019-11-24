package com.base.yun.mytestapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.adapter.MyAdapter
import com.base.yun.mytestapp.databinding.FragmentListBinding
import com.base.yun.mytestapp.utils.viewModels
import com.base.yun.mytestapp.viewmodel.MainActivityViewModel
import com.base.yun.mytestapp.widget.PriceSelectorItemViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class ListFragment : Fragment() {

    companion object {
        const val TAG = "ListFragment"
    }

    private val myAdapter: MyAdapter by lazy {
        MyAdapter()
    }

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false).apply {
            priceList.apply {
                setOnAddAmountClickListener {
                    Log.d(TAG, "setOnAddAmountClickListener")
                    amount_input.text.toString().toDoubleOrNull()?.let {
                        priceList.addManualItem(PriceSelectorItemViewModel.ViewModel("Set", it))
                        amount_input.text = null
                    }
                }
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo_list.adapter = myAdapter
        price_list.setPresetItems(arrayListOf(PriceSelectorItemViewModel.ViewModel("1st installment", 100.0),
                PriceSelectorItemViewModel.ViewModel("All received", 500.0),
                PriceSelectorItemViewModel.ViewModel("Not Received", 0.0)))
        price_list.setOnItemClickListener {
            val currency = 1000.0
            Log.d(TAG, "${currency + it}")
        }
    }
}