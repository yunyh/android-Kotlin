package com.example.younghyupyun.radiobuttonswiching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.younghyupyun.radiobuttonswiching.databases.AppDatabase
import com.example.younghyupyun.radiobuttonswiching.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val firstFragment by lazy {
        MainFragment.newInstance("button 1")
    }

    private val secondFragment by lazy {
        MainFragment.newInstance("button 2")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        radio_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_1 -> firstFragment
                R.id.radio_button_2 -> secondFragment
                else -> null
            }?.also {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commit()
            }
        }
    }
}
