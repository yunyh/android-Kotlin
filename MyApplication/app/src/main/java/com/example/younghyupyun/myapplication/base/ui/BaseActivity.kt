package com.example.younghyupyun.myapplication.base.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.younghyupyun.myapplication.base.annotations.ActivityUiType
import com.example.younghyupyun.myapplication.base.annotations.RequiredViewModel
import com.example.younghyupyun.myapplication.base.factories.BaseViewModelFactory
import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi

abstract class BaseActivity<VM : BaseViewModel, H : IBaseActivityUi> : AppCompatActivity(), IBaseActivityUi {

    protected abstract val uiHandler: H

    protected val viewModel by lazy {
        return@lazy checkNotNull(javaClass.getAnnotation(RequiredViewModel::class.java)).let {
            try {
                checkNotNull(javaClass.getAnnotation(ActivityUiType::class.java)) {
                    throw IllegalStateException("Requiring Activity Scope Annotation")
                }.let { scope ->
                    ViewModelProviders.of(this, BaseViewModelFactory.getInstance(uiHandler, scope.activityUiClass))
                        .get(it.viewModelClass.java) as VM
                }
            } catch (e: ClassCastException) {
                throw e
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setIntent(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}