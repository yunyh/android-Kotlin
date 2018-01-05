package com.base.yun.mytestapp.viewmodel

import android.arch.paging.ItemKeyedDataSource
import com.base.yun.mytestapp.model.MyModel

/**
 * Created by YounghyubYun on 2018. 1. 5..
 */


class MyDataSource : ItemKeyedDataSource<Int, MyModel>() {
    var dumpList = ArrayList<MyModel>()
    var dummyList2 = ArrayList<MyModel>()

    init {
        (0..12).mapTo(dummyList2, { MyModel(it + 30, "loadAfter dummy " + it) })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MyModel>) {
        //Do Nothing.
        if (params.key + 2 != dumpList.size + dummyList2.size) {
            callback.onResult(dummyList2)
        }
    }

    override fun getKey(item: MyModel): Int {
        return item.id
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MyModel>) {
        //Do Nothing.
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MyModel>) {
        if (dumpList.isEmpty()) {
            (0..30).mapTo(dumpList, { MyModel(it, "Dummy Data " + it) })
        }
        callback.onResult(dumpList)
    }
}