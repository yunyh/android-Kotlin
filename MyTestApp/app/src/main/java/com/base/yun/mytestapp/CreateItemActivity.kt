package com.base.yun.mytestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
        actionBar?.let {
            with(actionBar) {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
    }

    override fun onCreateSchedule() {
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "onBackPressed")
        when {
            currentFocus != null -> currentFocus.clearFocus()
        }
    }
}