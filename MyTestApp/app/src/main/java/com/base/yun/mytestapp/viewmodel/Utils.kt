package com.base.yun.mytestapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class SynchronizedViewModelLazy<out T>(private val initializer: (() -> T)?, lock: Any? = null) : Lazy<T> {

    @Volatile
    private var _value: Any? = UNINIT_VIEWMODEL

    private val lock = lock ?: this

    override val value: T
        get() {
            val _v1 = _value
            if (_v1 !== UNINIT_VIEWMODEL) {
                return _v1 as T
            }
            return synchronized(lock) {
                val _v2 = _value
                if (_v2 !== UNINIT_VIEWMODEL) {
                    _v2 as T
                } else {
                    val typedValue = initializer!!.invoke()
                    _value = typedValue
                    typedValue
                }
            }
        }

    override fun isInitialized(): Boolean = _value !== UNINIT_VIEWMODEL

    private object UNINIT_VIEWMODEL
}

inline fun <reified T : ViewModel> AppCompatActivity.viewModels(noinline initializer: (() -> T)? = null): Lazy<T> {
    val provider: () -> T = initializer ?: {
        ViewModelProviders.of(this)[T::class.java]
    }
    return SynchronizedViewModelLazy(provider)
}

