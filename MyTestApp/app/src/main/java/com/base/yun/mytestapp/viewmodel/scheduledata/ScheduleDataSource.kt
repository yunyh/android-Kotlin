package com.base.yun.mytestapp.viewmodel.scheduledata

import androidx.paging.ItemKeyedDataSource
import com.base.yun.mytestapp.provider.ScheduleEntity

/**
 * Created by YoungHyup on 2018-01-08.
 */

class ScheduleDataSource : ItemKeyedDataSource<Int, ScheduleEntity>() {

    override fun getKey(item: ScheduleEntity): Int {
        return item.id.toInt()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ScheduleEntity>) {
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ScheduleEntity>) {
        //callback.onResult(list)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ScheduleEntity>) {

    }

}