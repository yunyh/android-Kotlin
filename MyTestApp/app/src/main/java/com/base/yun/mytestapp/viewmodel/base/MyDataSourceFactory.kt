package com.base.yun.mytestapp.viewmodel.base

import androidx.paging.DataSource
import com.base.yun.mytestapp.model.MyModel

/**
 * Created by YounghyubYun on 2018. 1. 5..
 */
class MyDataSourceFactory : DataSource.Factory<Int, MyModel>() {
    override fun create(): DataSource<Int, MyModel> {
        return MyDataSource()
    }
}