package com.base.yun.mytestapp

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.base.yun.mytestapp.databinding.ActivityMainBinding
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import com.base.yun.mytestapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String? = "MainActivity"
    }

    private val lifeCycleObserver by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ActivityLifecycleObserver()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding)
                .model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java).apply {
            getService()
            getReceivedEvents("yunyh")
        }
        setSupportActionBar(findViewById(R.id.main_toolbar))

        supportFragmentManager?.run {
            beginTransaction().replace(R.id.fragment_container, ListFragment()).commit()
        }
    }

    override fun onDestroy() {
        with(lifecycle) {
            removeObserver(lifeCycleObserver)
        }
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.main_menu, it)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (item.itemId) {
                R.id.main_menu_item_create -> {
                    Log.d(TAG, "Main menu onClick")
                    startCreateItem()
                }
            }
            return true
        }
        return false
    }

    private fun startCreateItem() {
        val intent = Intent(this, CreateItemActivity::class.java)
        startActivity(intent)
    }
}
