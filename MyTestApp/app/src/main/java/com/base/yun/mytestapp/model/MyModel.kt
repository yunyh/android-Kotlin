package com.base.yun.mytestapp.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by YounghyubYun on 2017. 10. 5..
 */

open class MyModel(open var id: Int, open var data: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyModel> {
        override fun createFromParcel(parcel: Parcel): MyModel {
            return MyModel(parcel)
        }

        override fun newArray(size: Int): Array<MyModel?> {
            return arrayOfNulls(size)
        }
    }
}