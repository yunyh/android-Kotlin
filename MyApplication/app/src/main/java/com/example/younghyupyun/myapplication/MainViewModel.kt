package com.example.younghyupyun.myapplication

import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi

class MainViewModel(ui: MainActivityUi) : BaseViewModel(ui) {

    fun startUi() {
        ui.showToast("start ui")
    }

    interface MainActivityUi : IBaseActivityUi {
        fun action()
    }
}
