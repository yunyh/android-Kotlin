package com.example.younghyupyun.myapplication.base.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass

class BaseViewModelFactory<T : IBaseActivityUi> private constructor(
    private val baseActivityUi: T,
    private val activityUiClass: KClass<out IBaseActivityUi>
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        fun <T : IBaseActivityUi> getInstance(
            activityUi: T,
            activityUiClass: KClass<out IBaseActivityUi>
        ): BaseViewModelFactory<T> {
            return BaseViewModelFactory(activityUi, activityUiClass)
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            return try {
                val constructor = modelClass.getConstructor(activityUiClass.java)
                constructor.newInstance(baseActivityUi)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        } else throw RuntimeException("Cannot isAssignableFrom $modelClass")

    }
}