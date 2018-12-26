package com.example.younghyupyun.myapplication.base.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.younghyupyun.myapplication.base.annotations.ActivityUiType
import com.example.younghyupyun.myapplication.base.annotations.RequiredViewModel
import com.example.younghyupyun.myapplication.base.events.Event
import com.example.younghyupyun.myapplication.base.events.EventObserver
import com.example.younghyupyun.myapplication.base.factories.BaseViewModelFactory
import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi
import com.google.android.material.snackbar.Snackbar

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
        viewModel.observeSingleEvent(this, object : EventObserver {
            override fun onAction(event: Event) {
                when (event) {
                    is Event.ToastEvent -> Toast.makeText(baseContext, event.message, event.duration).show()
                    is Event.SnackbarEvent -> window?.decorView?.let { view ->
                        Snackbar.make(view, event.message, event.duration).show()
                    }
                    is Event.AlertDialogEvent -> AlertDialog.Builder(this@BaseActivity)
                        .setTitle(event.title)
                        .setMessage(event.message)
                        .setPositiveButton(event.positiveButtonText) { dialog, which -> dialog.dismiss() }
                        .setNegativeButton(event.negativeButtonText) { dialog, which -> dialog.dismiss() }
                }
            }
        })
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