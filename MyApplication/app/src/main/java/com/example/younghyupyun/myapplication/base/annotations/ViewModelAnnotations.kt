package com.example.younghyupyun.myapplication.base.annotations

import com.example.younghyupyun.myapplication.base.viewmodel.BaseViewModel
import com.example.younghyupyun.myapplication.base.viewmodel.IBaseActivityUi
import java.lang.annotation.Inherited
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KClass

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiredViewModel(
    val viewModelClass: KClass<out BaseViewModel>,
    val scope: LifecycleScope = LifecycleScope.ACTIVITY
)

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityUiType(val activityUiClass: KClass<out IBaseActivityUi>)

enum class LifecycleScope {
    ACTIVITY, FRAGMENT
}