package com.example.younghyupyun.myapplication

import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi

class MainViewModel(ui: MainActivityUi) : BaseViewModel(ui) {

    fun startUi() {
        ui.showToast("start ui")
    }
    fun testSnackbar(){
        singleEvent.showSnackbar("Test show snack bar")
    }

    interface MainActivityUi : IBaseActivityUi {
        fun action()
    }
}
