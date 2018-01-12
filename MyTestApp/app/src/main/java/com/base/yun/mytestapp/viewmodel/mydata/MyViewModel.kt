package com.base.yun.mytestapp.viewmodel.mydata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.base.yun.mytestapp.model.MyModel
import com.base.yun.mytestapp.provider.MyDataBase
import com.base.yun.mytestapp.provider.ScheduleEntity
import com.base.yun.mytestapp.viewmodel.scheduledata.ScheduleDataSourceFactory

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

open class MyViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        private const val TAG: String = "MyViewModel"
        private const val PAGE_SIZE = 30
    }

    protected val scheduleDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        MyDataBase.provider(app.applicationContext).myScheduleDao()
    }

    @MainThread
    fun providerList(): LiveData<PagedList<MyModel>> {
        return LivePagedListBuilder(MyDataSourceFactory(), PAGE_SIZE).build()
    }

    @MainThread
    fun providerSchedule(): LiveData<PagedList<ScheduleEntity>> {
        return LivePagedListBuilder(ScheduleDataSourceFactory(), PAGE_SIZE).build()
    }
}