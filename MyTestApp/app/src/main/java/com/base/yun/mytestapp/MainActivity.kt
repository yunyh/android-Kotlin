package com.base.yun.mytestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.base.yun.mytestapp.databinding.ActivityMainBinding
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import com.base.yun.mytestapp.viewmodel.MainActivityViewModel
import com.base.yun.mytestapp.viewmodel.viewModels

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String? = "MainActivity"
    }

    private val lifeCycleObserver = ActivityLifecycleObserver()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = viewModel
        Log.d(TAG, viewModel.toString())
        //ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        setSupportActionBar(binding.mainToolbar)
        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, ListFragment())?.commitNow()

    }

    override fun onDestroy() {
        Log.d(TAG, viewModel.toString())
        lifecycle.removeObserver(lifeCycleObserver)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, viewModel.toString())
        menu?.let {
            menuInflater.inflate(R.menu.main_menu, it)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(TAG, viewModel.toString())
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
        startActivity(Intent(this, CreateItemActivity::class.java))
    }
}
