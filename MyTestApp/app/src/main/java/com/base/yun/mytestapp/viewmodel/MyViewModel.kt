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

class MyViewModel(app: Application) : AndroidViewModel(app) {

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
        private val list = ArrayList<MyModel>()

        init {
            (0..1000).mapTo(list) { MyModel(it, "Data " + it) }
        }

        override fun loadBefore(currentBeginKey: Int, pageSize: Int): MutableList<MyModel> {
            Log.d(TAG, "loadBefore : " + currentBeginKey)
            Log.d(TAG, "loadBefore : " + pageSize)
            val beforeList = ArrayList<MyModel>()

            repeat(list.size) { index ->
                if (currentBeginKey > list[index].id) {
                    beforeList.add(list[index])
                }
            }
            return beforeList
        }

        override fun loadAfter(currentEndKey: Int, pageSize: Int): MutableList<MyModel> {
            Log.d(TAG, "loadAfter : " + currentEndKey)
            Log.d(TAG, "loadAfter : " + pageSize)

            val afterList = ArrayList<MyModel>()

            repeat(list.size) { index ->
                if (currentEndKey < list[index].id) {
                    afterList.add(list[index])
                }
            }

            return afterList
        }

        override fun loadInitial(pageSize: Int): MutableList<MyModel> {
            // Log.d(TAG, "loadInitial : " + pageSize)
            val list = ArrayList<MyModel>(pageSize)
            repeat(pageSize) { index -> list.add(this.list[index]) }
            return list
        }

        override fun getKey(item: MyModel): Int {
            return item.id
        }
    }
}