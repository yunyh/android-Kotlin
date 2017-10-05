package com.base.yun.mytestapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.DataSource
import android.arch.paging.KeyedDataSource
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList
import android.util.Log
import com.base.yun.mytestapp.model.MyModel

/**
 * Created by YounghyubYun on 2017. 10. 4..
 */
val dumpList = ArrayList<MyModel>()

class MyViewModel(app: Application) : AndroidViewModel(app) {

    init {
        if (dumpList.isEmpty()) {
            (0..30).mapTo(dumpList) { MyModel(it, "Data " + it) }
        }
    }

    companion object {

        private const val TAG: String = "MyViewModel"
        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDERS = true
    }

    private fun allData(): LivePagedListProvider<Int, MyModel> {
        return object : LivePagedListProvider<Int, MyModel>() {
            override fun createDataSource(): DataSource<Int, MyModel> = MyDataSource()
        }
    }

    val dump = allData().create(0, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE).setInitialLoadSizeHint(PAGE_SIZE).setEnablePlaceholders(ENABLE_PLACEHOLDERS).build()!!)

    class MyDataSource : KeyedDataSource<Int, MyModel>() {

        override fun loadBefore(currentBeginKey: Int, pageSize: Int): MutableList<MyModel> {
            Log.d(TAG, "loadBefore currentBeginKey: " + currentBeginKey)
            val beforeList = ArrayList<MyModel>()

            repeat(dumpList.size) { index ->
                if (currentBeginKey > dumpList[index].id) {
                    beforeList.add(dumpList[index])
                }
            }
            return beforeList
        }

        override fun loadAfter(currentEndKey: Int, pageSize: Int): MutableList<MyModel> {
            Log.d(TAG, "loadAfter currentEndKey: " + currentEndKey)

            val afterList = ArrayList<MyModel>()

            repeat(dumpList.size) { index ->
                if (currentEndKey < dumpList[index].id) {
                    afterList.add(dumpList[index])
                }
            }

            return afterList
        }

        override fun loadInitial(pageSize: Int): MutableList<MyModel> {
            return dumpList
        }

        override fun getKey(item: MyModel): Int {
            return item.id
        }
    }
}