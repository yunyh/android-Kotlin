package com.base.yun.mytestapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.base.BaseFragment
import com.base.yun.mytestapp.viewmodel.mydata.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_create_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class CreateScheduleFragment : BaseFragment<ScheduleViewModel>() {
    override fun setViewModel(): Class<ScheduleViewModel> {
        return ScheduleViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_create_date.text = getString(R.string.current_time_desc, viewModel.setCurrentTimeMileSecond())
    }
}