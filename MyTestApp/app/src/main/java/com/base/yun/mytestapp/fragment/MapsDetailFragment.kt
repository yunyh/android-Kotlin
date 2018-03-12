package com.base.yun.mytestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.fragment.base.BaseFragment
import com.base.yun.mytestapp.model.MapsDetailViewModel

/**
 * Created by mark.yun on 2018. 3. 12..
 */
class MapsDetailFragment : BaseFragment<MapsDetailViewModel>() {
    override fun setViewModel(): Class<MapsDetailViewModel> {
        return MapsDetailViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps_detail, container, false)
    }
}