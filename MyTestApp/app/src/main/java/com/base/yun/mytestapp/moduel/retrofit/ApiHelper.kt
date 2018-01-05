package com.base.yun.mytestapp.moduel.retrofit

/**
 * Created by YounghyubYun on 2017. 10. 10..
 */

class ApiHelper private constructor(){

    private object Holder {
        val INSTANCE = ApiHelper()
    }

    companion object {
        val instance: ApiHelper by lazy { Holder.INSTANCE }
    }
}