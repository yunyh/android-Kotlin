package com.base.yun.mytestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var lifeCycleObserver : ActivityLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(main_fragment_container.id, ListFragment()).commit()

        lifeCycleObserver = ActivityLifecycleObserver()
        lifecycle.addObserver(lifeCycleObserver)
    }
}