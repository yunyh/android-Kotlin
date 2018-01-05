package com.base.yun.mytestapp.viewmodel

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
        private const val ENABLE_PLACEHOLDERS = true
    }

    /* val dump = allData().create(0, PagedList.Config.Builder()
             .setPageSize(PAGE_SIZE).setInitialLoadSizeHint(PAGE_SIZE).setEnablePlaceholders(ENABLE_PLACEHOLDERS).build()!!)
   */
    @MainThread
    fun providerList(): LiveData<PagedList<MyModel>> {
        return LivePagedListBuilder(MyDataSourceFactory(), PAGE_SIZE).build();
    }
}