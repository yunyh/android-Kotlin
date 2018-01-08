package com.base.yun.mytestapp.viewmodel.mydata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.base.yun.mytestapp.model.MyModel

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */

class MyViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        private const val TAG: String = "MyViewModel"
        private const val PAGE_SIZE = 30
    }

    @MainThread
    fun providerList(): LiveData<PagedList<MyModel>> {
        return LivePagedListBuilder(MyDataSourceFactory(), PAGE_SIZE).build()
    }
}