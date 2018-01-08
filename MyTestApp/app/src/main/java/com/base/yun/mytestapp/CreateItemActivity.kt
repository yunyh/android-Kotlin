package com.base.yun.mytestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */
class CreateItemActivity : AppCompatActivity() {

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
}