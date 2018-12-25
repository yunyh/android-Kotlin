package com.example.younghyupyun.myapplication.base.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel

abstract class BaseViewModel protected constructor(protected val ui: IBaseActivityUi) : ViewModel() {

    fun setIntent(intent: Intent?) {

    }
}