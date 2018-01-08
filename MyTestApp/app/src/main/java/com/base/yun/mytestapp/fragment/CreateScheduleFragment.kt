package com.base.yun.mytestapp.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.base.BaseFragment
import com.base.yun.mytestapp.viewmodel.scheduledata.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_create_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class CreateScheduleFragment : BaseFragment<ScheduleViewModel>() {

    private lateinit var callback: CreateScheduleFragmentCallback

    override fun setViewModel(): Class<ScheduleViewModel> {
        return ScheduleViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.let {
            inflater.inflate(R.menu.create_menu, menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            with(callback) {
                when (item.itemId) {
                    R.id.create_menu_item_done -> {
                        viewModel.createMySchedule(fragment_create_date.text.toString(), fragment_create_desc.text.toString())
                        onCreateSchedule()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_create_date.text = getString(R.string.current_time_desc, viewModel.setCurrentTimeMileSecond())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CreateScheduleFragmentCallback) {
            callback = context
        }
    }

    interface CreateScheduleFragmentCallback {
        fun onCreateSchedule()
    }
}