package com.base.yun.mytestapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.base.yun.mytestapp.fragment.DetailFragment
import com.base.yun.mytestapp.fragment.ListFragment
import com.base.yun.mytestapp.lifecycle.ActivityLifecycleObserver
import com.base.yun.mytestapp.model.MyModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.ListFragmentCallback {

    companion object {
        val TAG: String? = "MainActivity"
    }

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

    private fun showDetailFragment(item: MyModel) {
        with(supportFragmentManager) {
            beginTransaction()
                    .addToBackStack("list")
                    .replace(main_fragment_container.id, DetailFragment.newInstance(item))
                    .commit()
        }
    }

    private fun startCreateItem() {
        val intent = Intent(this, CreateItemActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClickListener(item: MyModel) {
        showDetailFragment(item)
    }
}