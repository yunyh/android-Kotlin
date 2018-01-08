package com.base.yun.mytestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.base.yun.mytestapp.fragment.DetailFragment
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import com.base.yun.mytestapp.model.MyModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.ListFragmentCallback {


    private val lifeCycleObserver by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ActivityLifecycleObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(lifecycle) {
            addObserver(lifeCycleObserver)
        }

        with(supportFragmentManager) {
            beginTransaction()
                    .replace(main_fragment_container.id, ListFragment(), "list")
                    .commit()
        }
    }

    override fun onDestroy() {
        with(lifecycle) {
            removeObserver(lifeCycleObserver)
        }
        super.onDestroy()
    }

    private fun showDetailFragment(item: MyModel) {
        with(supportFragmentManager) {
            beginTransaction()
                    .addToBackStack("list")
                    .replace(main_fragment_container.id, DetailFragment.newInstance(item))
                    .commit()
        }
    }

    override fun onItemClickListener(item: MyModel) {
        showDetailFragment(item)
    }
}