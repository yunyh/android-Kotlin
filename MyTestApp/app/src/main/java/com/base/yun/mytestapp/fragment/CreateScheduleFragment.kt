package com.base.yun.mytestapp.fragment


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.fragment.base.BaseFragment
import com.base.yun.mytestapp.viewmodel.scheduledata.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_create_schedule.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CreateScheduleFragment : BaseFragment<ScheduleViewModel>() {

    companion object {
        private val TAG = "CreateScheduleFragment"
    }

    private lateinit var callback: CreateScheduleFragmentCallback

    private lateinit var scroll: NestedScrollView

    private val datePickerDialog by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DatePickerDialog.OnDateSetListener { _, year, month, date -> setDate(year, month, date) }
    }

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
                        viewModel.createMySchedule(fragment_create_title.text.toString(), fragment_create_desc.text.toString())
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
        val button = fragment_create_test_button
        button.visibility = View.VISIBLE
        fragment_create_date.text = getString(R.string.current_time_desc, viewModel.setCurrentTimeMileSecond())
        fragment_create_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (TextUtils.isEmpty(text)) {
                    return
                }
                //   button.visibility = View.VISIBLE
                //   scroll()
            }
        })
        fragment_create_title.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    //  scroll()
                    false
                }
                else -> false
            }
        }
        scroll = fragment_create_scroll_view
        val title = fragment_create_title

        title.setOnFocusChangeListener { _, boolean ->
            if (boolean) {
                title.postDelayed({
                    if (title.isFocusable) {
                        if (fragment_create_scroll_view == null) {
                            //   Log.d(TAG, " fragment_create_scroll_view == null");
                        } else {
                            //  scroll()
                        }
                    }
                }, 500L)
            }
        }

        fragment_create_date.setOnClickListener {

            val year = Calendar.getInstance().get(Calendar.YEAR)
            val month = Calendar.getInstance().get(Calendar.MONTH)
            val dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context, this.datePickerDialog, year, month, dayOfMonth)
            datePickerDialog.show()
        }
    }

    private fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        fragment_create_date.text = viewModel.setChangedTime(year, month, dayOfMonth)
    }

    private fun scroll() {

        val imm: InputMethodManager? = activity!!
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.let {
            if (imm.isAcceptingText) {
                Log.d(TAG, "Software Keyboard was shown")
                with(scroll) {
                    isSmoothScrollingEnabled = true
                    fragment_create_scroll_view.postDelayed({
                        with(fragment_create_scroll_view) {
                            scrollTo(0, fragment_create_test_button.bottom)
                        }
                    }, 200L)
                    return@let
                }
            } else {
                Log.d(TAG, "Software Keyboard was not shown")
            }
        }
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