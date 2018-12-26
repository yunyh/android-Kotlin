package com.example.younghyupyun.myapplication

import android.os.Bundle
import com.example.younghyupyun.myapplication.base.ui.BaseActivity
import com.example.younghyupyun.myapplication.base.annotations.ActivityUiType
import com.example.younghyupyun.myapplication.base.annotations.RequiredViewModel
import kotlinx.android.synthetic.main.activity_main.*

@RequiredViewModel(MainViewModel::class)
@ActivityUiType(MainViewModel.MainActivityUi::class)
class MainActivity : BaseActivity<MainViewModel, MainViewModel.MainActivityUi>(), MainViewModel.MainActivityUi {

    override fun action() {

    }

    override val uiHandler: MainViewModel.MainActivityUi
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { viewModel.testSnackbar() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUi()
    }
}