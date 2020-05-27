package com.base.yun.mytestapp.viewmodel.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.annotation.MainThread
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.provider.MyDataBase
import com.base.yun.mytestapp.provider.ScheduleEntity

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

open class MyViewModel(private val app: Application) : AndroidViewModel(app) {

    companion object {
        private const val TAG: String = "MyViewModel"
        private const val PAGE_SIZE = 10
    }

    protected val scheduleDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        MyDataBase.provider(getApplication()).myScheduleDao()
    }

    @MainThread
    fun providerList(): LiveData<PagedList<MyModel>> {
        return LivePagedListBuilder(MyDataSourceFactory(), PAGE_SIZE).build()
    }

    fun providerSchedule(): LiveData<PagedList<ScheduleEntity>> {
        return LivePagedListBuilder(scheduleDao.getAll(), PAGE_SIZE).build()
    }
}