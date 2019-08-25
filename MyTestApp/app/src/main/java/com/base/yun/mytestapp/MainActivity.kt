package com.base.yun.mytestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.base.yun.mytestapp.databinding.ActivityMainBinding
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import com.base.yun.mytestapp.utils.bindingView
import com.base.yun.mytestapp.utils.provideViewModel
import com.base.yun.mytestapp.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String? = "MainActivity"
    }

    private val lifeCycleObserver = ActivityLifecycleObserver()

    private val binding: ActivityMainBinding by bindingView(R.layout.activity_main)

    private val viewModel: MainActivityViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = viewModel
        setSupportActionBar(binding.mainToolbar)
        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, ListFragment())?.commitNow()
    }

    override fun onDestroy() {
        lifecycle.removeObserver(lifeCycleObserver)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return menu?.let {
            menuInflater.inflate(R.menu.main_menu, it)
            true
        } ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(TAG, viewModel.toString())
        return item?.let {
            when (item.itemId) {
                R.id.main_menu_item_create -> {
                    Log.d(TAG, "Main menu onClick")
                    startCreateItem()
                }
            }
            true
        } ?: false
    }

    private fun startCreateItem() {
        startActivity(Intent(this, CreateItemActivity::class.java))
    }
}
