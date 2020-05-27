package com.base.yun.mytestapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.base.yun.mytestapp.fragment.CreateScheduleFragment

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */
class CreateItemActivity : AppCompatActivity(), CreateScheduleFragment.CreateScheduleFragmentCallback {

    companion object {
        private val TAG = "CreateItemActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    override fun onCreateSchedule() {
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        currentFocus?.clearFocus()
    }
}